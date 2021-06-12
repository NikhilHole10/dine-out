package com.spring.dineout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findAllByRestaurant(Restaurant restaurant);
	
}
