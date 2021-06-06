package com.spring.dineout.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.model.Booking;
import com.spring.dineout.service.BookingService;
import static org.springframework.http.ResponseEntity.status;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
	
	private final BookingService bookingService;
	
	@GetMapping("/viewbookings/{userId}")
	public ResponseEntity<Booking> getAllBookings(@PathVariable Long userId) {
		System.out.println(bookingService.findAllBookingsById(userId));
		return status(HttpStatus.OK).body(bookingService.findAllBookingsById(userId));
	}
}
