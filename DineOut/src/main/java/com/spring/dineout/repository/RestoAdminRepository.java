package com.spring.dineout.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.User;


@Repository
public interface RestoAdminRepository extends JpaRepository<Restaurant,Long> {
	Optional<Restaurant> findByUserId(Long UserId);

	//Optional<Restaurant> findByEmail(String username);
}
