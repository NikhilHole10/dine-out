package com.spring.dineout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dineout.dto.ReviewRequest;
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
		Review review = reviewMapper.map(reviewRequest,authService.getCurrentCustomer(), restaurant);
		reviewRepository.save(review);
	}
	
	
}
