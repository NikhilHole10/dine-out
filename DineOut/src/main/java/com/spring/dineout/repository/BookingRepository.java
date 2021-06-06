package com.spring.dineout.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	Optional<Booking> findByBookingId(Long userId);
}
