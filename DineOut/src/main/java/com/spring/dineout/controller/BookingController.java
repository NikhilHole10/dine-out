package com.spring.dineout.controller;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.model.Booking;
import com.spring.dineout.service.BookingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
	
	private final BookingService bookingService;
	
	@GetMapping("/viewbookings/{userid}")
	public Booking getAllBookings(@PathVariable Long userid) {
		
		return bookingService.findAllBookingsById(userid);
		
	}
}
