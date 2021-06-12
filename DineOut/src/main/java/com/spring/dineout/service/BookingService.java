package com.spring.dineout.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.BookingRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.model.Customer;
import com.spring.dineout.model.NotificationEmail;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.User;
import com.spring.dineout.repository.BookingRepository;
import com.spring.dineout.repository.CustomerRepository;
import com.spring.dineout.repository.RestaurantRepository;
import com.spring.dineout.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {
	private final BookingRepository bookingRepository;
	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;
	private final CustomerService customerService;
	private final MailContentBuilder mailContentBuilder;
	private final MailService mailService;
	private final AuthService authService;

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
		booking.setGuestName(bookingRequest.getGuestName());
		booking.setBookedDate(bookedDate);
		booking.setBookedStatus(false);
		booking.setCreatedDate(Instant.now());
		Booking savedBooking = bookingRepository.save(booking);

		sendEmailNotificationToCustomer(bookingRequest.getRestoId());
		sendEmailNotificationToRestaurant(bookingRequest);
	}
	private void sendEmailNotificationToCustomer(Long restoId) {
		Restaurant restaurant = restaurantRepository.findById(restoId).get();
		//String message = mailContentBuilder.build("Your order at "+restaurant.getRestoName()+" has been placed successfully. Order confirmation will take upto 10 mins. Thank you, Team Dineout");
		
		mailService.sendMail(new NotificationEmail("Order Received",authService.getCurrentUser().getEmail(),"Your order at "+restaurant.getRestoName()+" has been placed successfully. Order confirmation will take upto 10 mins. Thank you, Team Dineout")); 
	}
	private void sendEmailNotificationToRestaurant(BookingRequest bookingRequest) {
		Customer customer = customerRepository.findByUserId(authService.getCurrentUser().getUserId()).get();
		Restaurant restaurant = restaurantRepository.findByRestoId(bookingRequest.getRestoId()).get();
		User user = userRepository.findById(restaurant.getUserId()).get();
		//String message = mailContentBuilder.build();
		
		mailService.sendMail(new NotificationEmail("New Order Received",user.getEmail(),"New Order has been placed by "+customer.getCustomerName()+" on "+bookingRequest.getBookedDate()+". Please review the order. Thank You,Team Dineout")); 
	}

}
