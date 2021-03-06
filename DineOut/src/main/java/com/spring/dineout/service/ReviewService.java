package com.spring.dineout.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dineout.dto.ReviewRequest;
import com.spring.dineout.dto.ReviewResponse;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.mapper.ReviewMapper;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Review;
import com.spring.dineout.repository.RestaurantRepository;
import com.spring.dineout.repository.ReviewRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;
	private final ReviewMapper reviewMapper;
	private final AuthService authService;

	public void createReview(ReviewRequest reviewRequest) {
		Restaurant restaurant = restaurantRepository.findById(reviewRequest.getRestoId()).orElseThrow(()-> new DineOutException("Restaurant does not exists"));
		
		reviewRepository.save( reviewMapper.map(reviewRequest,authService.getCurrentCustomer(), restaurant));
	}

	public List<ReviewResponse> getReviewsByRestaurant(Long restoId) {
		Restaurant restaurant = restaurantRepository.findById(restoId).orElseThrow(()-> new DineOutException("Invalid Restaurant"));
		return reviewRepository.findAllByRestaurant(restaurant)
				.stream()
				.map(reviewMapper::mapReviewEntityToResponse)
				.collect(Collectors.toList());
	}

}
