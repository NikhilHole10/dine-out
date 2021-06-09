package com.spring.dineout.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.BookingRequest;
import com.spring.dineout.model.Booking;
import com.spring.dineout.model.User;
import com.spring.dineout.service.BookingService;
import static org.springframework.http.ResponseEntity.status;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
	
	private final BookingService bookingService;
	

	@GetMapping("/viewbookings/{userId}")
  @PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Booking> getAllBookings(@PathVariable Long userId) {
		System.out.println(bookingService.findAllBookingsById(userId));
		return status(HttpStatus.OK).body(bookingService.findAllBookingsById(userId));
	}
	
	@PostMapping("/bookSeats")
	@PreAuthorize("hasAuthority('USER')")
	public void bookSeats(@RequestBody BookingRequest bookingRequest) {
		bookingService.bookSeats(bookingRequest);
	}
	
	@GetMapping("/pendingApprovals")
	@PreAuthorize("hasAuthority('RESTOADMIN')")
	public List<Booking> pendingApprovals() {
		return bookingService.getPendingApprovals();
	}
}
