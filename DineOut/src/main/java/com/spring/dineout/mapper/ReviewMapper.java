package com.spring.dineout.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.spring.dineout.dto.ReviewRequest;
import com.spring.dineout.model.Customer;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Review;
import com.spring.dineout.model.User;


@Mapper(componentModel = "spring")
public interface ReviewMapper {

	@Mapping(target = "reviewId", ignore = true)
    @Mapping(target = "review", source = "reviewRequest.review")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "restaurant", source = "restaurant")
    @Mapping(target = "customer", source = "customer")
	Review map(ReviewRequest reviewRequest,Customer customer,Restaurant restaurant);
}
