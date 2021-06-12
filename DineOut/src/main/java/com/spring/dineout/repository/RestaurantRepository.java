package com.spring.dineout.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dineout.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

	Optional<Restaurant> findByRestoId(Long restoId);

	

}
