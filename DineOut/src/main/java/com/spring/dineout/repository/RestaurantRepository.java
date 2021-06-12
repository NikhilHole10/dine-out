package com.spring.dineout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dineout.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
