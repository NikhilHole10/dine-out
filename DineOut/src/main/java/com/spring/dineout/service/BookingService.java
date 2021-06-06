package com.spring.dineout.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.repository.BookingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {
	private final BookingRepository bookingRepository;
	
	public Booking findAllBookingsById(Long userId) {
		return bookingRepository.findByBookingId(userId).orElseThrow(()->new DineOutException("No bookings found"));
	}
}
