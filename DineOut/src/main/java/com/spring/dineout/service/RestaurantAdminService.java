package com.spring.dineout.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.CustomerRegistrationRequest;
import com.spring.dineout.dto.RestoAdminRegisterRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Booking;
import com.spring.dineout.model.Customer;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.User;
import com.spring.dineout.repository.BookingRepository;
import com.spring.dineout.repository.CustomerRepository;
import com.spring.dineout.repository.RestoAdminRepository;
import com.spring.dineout.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantAdminService {
	
	private final BookingRepository bookingRepository;
	private final UserRepository userRepository; 
	private final RestoAdminRepository restoAdminRepository;
	public List<Booking> getPendingApprovals() {
		UserDetails loggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loggedUserUserName = loggedUser.getUsername();
		User user = userRepository.findByEmail(loggedUserUserName)
				.orElseThrow(()->new DineOutException("logged user "+loggedUserUserName+" not found"));
		
		Optional<List<Booking>> approvals= bookingRepository.getPendingApproval(user.getUserId());
		
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
		restaurant.setAddress(restoAdminRegisterRequest.getAddress());
		restaurant.setMealType(restoAdminRegisterRequest.getMealType());
		restaurant.setRestoStatus(false);
		restoAdminRepository.save(restaurant);
		
		}
	}
}
