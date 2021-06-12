package com.spring.dineout.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.spring.dineout.dto.ReviewRequest;
import com.spring.dineout.dto.ReviewResponse;
import com.spring.dineout.model.Customer;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Review;
import com.spring.dineout.model.User;


@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

	@Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "review", source = "reviewRequest.review")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "customer", source = "customer")
	public abstract Review map(ReviewRequest reviewRequest,Customer customer,Restaurant restaurant);

	@Mapping(target = "userName", expression ="java(review.getCustomer().getCustomerName())")
	@Mapping(target = "duration", expression = "java(getDuration(review))")
	public abstract ReviewResponse mapReviewEntityToResponse(Review review);
	
	String getDuration(Review review) {
		return TimeAgo.using(review.getCreatedDate().toEpochMilli());
	}
	
}
