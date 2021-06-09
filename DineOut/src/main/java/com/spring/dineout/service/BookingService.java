package com.spring.dineout.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.BookingRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.model.User;
import com.spring.dineout.repository.BookingRepository;
import com.spring.dineout.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {
	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	
	public Booking findAllBookingsById(Long userId) {
		return bookingRepository.findByBookingId(userId).orElseThrow(()->new DineOutException("No bookings found"));
	}
	
public void bookSeats(BookingRequest bookingRequest) {
		
		User resto = userRepository.findByEmail(bookingRequest.getRestaurentEmail())
				.orElseThrow(()-> new DineOutException("No email found"));
		
		
		
		if (resto.getRoleEnum().toString()!="RESTOADMIN") {
			throw new DineOutException("No resto found");
		}
		
		Date bookedDate = null;
		try {
			bookedDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookingRequest.getBookingDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int bookedSeats =0;
		Optional<Integer> oldBooking = bookingRepository.findAvailability(resto.getUserId(),bookedDate);
		if(oldBooking.isPresent()) {
			bookedSeats=oldBooking.get();
		}
		
		UserDetails loggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedUserUserName = loggedUser.getUsername();
		User user = userRepository.findByEmail(loggedUserUserName)
				.orElseThrow(()->new DineOutException("logged user "+loggedUserUserName+" not found"));
		Integer leftSeats =resto.getTotalSeats()-bookedSeats;
		
		if(leftSeats>=bookingRequest.getSeats()) {
			Booking booking = new Booking(
					user.getUserId(),
					resto.getUserId(),
					bookingRequest.getSeats(),
					Instant.now(),
					false,
					bookedDate
					);
			
			bookingRepository.save(booking);
		}
		else {
			throw new DineOutException("Only "+leftSeats.toString() +" seats left");
		}
		
		
		
	}
		
	public List<Booking> getPendingApprovals() {
		UserDetails loggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedUserUserName = loggedUser.getUsername();
		User user = userRepository.findByEmail(loggedUserUserName)
				.orElseThrow(()->new DineOutException("logged user "+loggedUserUserName+" not found"));
		
		Optional<List<Booking>> approvals= bookingRepository.getPendingApproval(user.getUserId());
		
		return approvals.get();
	}
}
