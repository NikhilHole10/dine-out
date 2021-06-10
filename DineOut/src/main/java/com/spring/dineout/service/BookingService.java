package com.spring.dineout.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.BookingRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.repository.BookingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {
	private final BookingRepository bookingRepository;
	private final CustomerService customerService;

	public Booking findAllBookingsById(Long userId) {
		return bookingRepository.findByBookingId(userId).orElseThrow(()->new DineOutException("No bookings found"));
	}
	
public void bookSeats(BookingRequest bookingRequest) {
		
		Date bookedDate = null;
		try {
			bookedDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookingRequest.getBookedDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Booking booking = new Booking();
		booking.setRestoId(bookingRequest.getRestoId());
		booking.setCustomerId(customerService.getLoggedCustomer().getUserId());
		booking.setSlotId(bookingRequest.getSlotId());
		booking.setBookedSeats(bookingRequest.getBookedSeats());
		booking.setGuestName(booking.getGuestName());
		booking.setBookedDate(bookedDate);
		booking.setBookedStatus(false);
		booking.setCreatedDate(Instant.now());
		bookingRepository.save(booking);
		}
		
	
}
