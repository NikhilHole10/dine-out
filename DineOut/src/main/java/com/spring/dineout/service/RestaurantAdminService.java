package com.spring.dineout.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.RestoAdminRegisterRequest;
import com.spring.dineout.dto.SlotRegisterRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Slots;
import com.spring.dineout.model.User;
import com.spring.dineout.repository.BookingRepository;
import com.spring.dineout.repository.RestoAdminRepository;
import com.spring.dineout.repository.SlotRepository;
import com.spring.dineout.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantAdminService {
	
	private final BookingRepository bookingRepository;
	private final UserRepository userRepository; 
	private final RestoAdminRepository restoAdminRepository;
	private final SlotRepository slotRepository;
	
	public Restaurant getLoggedRestoAdmin(){
		UserDetails loggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user =  userRepository.findByEmail(loggedUser.getUsername());
		return restoAdminRepository.findByUserId(user.get().getUserId()).get();
	}
	
	public List<Booking> getPendingApprovals() {
		Optional<List<Booking>> approvals= bookingRepository.getPendingApproval(getLoggedRestoAdmin().getUserId());
		
		return approvals.get();
	}
	
	public boolean findByRestaurantEmail(String email) {
		Long userId=null;
		Optional<User> user =  userRepository.findByEmail(email);
		if(user.isPresent()) {
			userId=user.get().getUserId();
			if(restoAdminRepository.findByUserId(userId).isPresent()) {
				return true;
			}
		}
		return false;
	}


	public void saveRestaurantDetails(RestoAdminRegisterRequest restoAdminRegisterRequest) {

		UserDetails userDetail =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userRepository.findByEmail(userDetail.getUsername());
		if(findByRestaurantEmail(user.get().getEmail())) {
			Optional<Restaurant> restaurant= restoAdminRepository.findByUserId(user.get().getUserId());
				restaurant.get().setUserId(user.get().getUserId());
				restaurant.get().setRestoName(restoAdminRegisterRequest.getRestoName());
				restaurant.get().setOwnerName(restoAdminRegisterRequest.getOwnerName());
				restaurant.get().setContactNo(restoAdminRegisterRequest.getContactNo());
				restaurant.get().setAddress(restoAdminRegisterRequest.getAddress());
				restaurant.get().setCity(restoAdminRegisterRequest.getCity());
				restaurant.get().setMealType(restoAdminRegisterRequest.getMealType());
				restoAdminRepository.save(restaurant.get());
		}else {
		Restaurant restaurant = new Restaurant();
		restaurant.setUserId(user.get().getUserId());
		restaurant.setRestoName(restoAdminRegisterRequest.getRestoName());
		restaurant.setOwnerName(restoAdminRegisterRequest.getOwnerName());
		restaurant.setContactNo(restoAdminRegisterRequest.getContactNo());
		restaurant.setAddress(restoAdminRegisterRequest.getAddress());
		restaurant.setCity(restoAdminRegisterRequest.getCity());
		restaurant.setMealType(restoAdminRegisterRequest.getMealType());
		restaurant.setRestoStatus(false);
		restoAdminRepository.save(restaurant);
		
		}
	}

	public void addSlots(List<SlotRegisterRequest> listOfSlots) {
		for(int i=0;i<listOfSlots.size();i++) {
			Slots s = new Slots();
			Date bookedDate = null;
			try {
				bookedDate = new SimpleDateFormat("dd/MM/yyyy").parse(listOfSlots.get(i).getSlotDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.setRestoId(listOfSlots.get(i).getRestoid());
			s.setSlotDate(bookedDate);
			s.setTimeSlot(listOfSlots.get(i).getTime());
			s.setCapacity(listOfSlots.get(i).getCapacity());
			slotRepository.save(s);
			}
		
	}

	public void approveOrder(Long bookingid) {
		Optional<Booking> booking =  bookingRepository.findByBookingId(bookingid);
		Optional<Slots> slot = slotRepository.findBySlotId(booking.get().getSlotId());
		if(slot.get().getCapacity()<=booking.get().getBookedSeats()) {
			throw new DineOutException("Bookgin not confirmed");
		}
		else {
			slot.get().setCapacity(slot.get().getCapacity()-booking.get().getBookedSeats());
			booking.get().setBookedStatus(true);	
			bookingRepository.save(booking.get());
			slotRepository.save(slot.get());
		}
	}
}
