package com.spring.dineout.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.ReviewRequest;
import com.spring.dineout.dto.ReviewResponse;
import com.spring.dineout.service.ReviewService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/review/")
@RestController
@AllArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Void> addComment(@RequestBody ReviewRequest reviewRequest) {
		reviewService.createReview(reviewRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("by-restaurant/{restoId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<List<ReviewResponse>> getAllReviewsForRestaurant(@PathVariable Long restoId ) {
		return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewsByRestaurant(restoId));
		}
}
