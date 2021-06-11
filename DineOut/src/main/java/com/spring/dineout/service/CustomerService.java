package com.spring.dineout.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.CustomerRegistrationRequest;
import com.spring.dineout.model.Customer;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.User;
import com.spring.dineout.repository.CustomerRepository;
import com.spring.dineout.repository.RestoAdminRepository;
import com.spring.dineout.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final UserRepository userRepository;
	private final RestoAdminRepository restoAdminRepository;
	
	public boolean findByCustomerName(String email) {
		Long userId=null;
		Optional<User> user =  userRepository.findByEmail(email);
		if(user.isPresent()) {
			userId=user.get().getUserId();
			if(customerRepository.findByUserId(userId).isPresent()) {
				return true;
			}
		}
		return false;
	}

	public void saveCustomerDetails(CustomerRegistrationRequest customerRegistrationRequest) {
		
		UserDetails userDetail =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userRepository.findByEmail(userDetail.getUsername());
		if(findByCustomerName(user.get().getEmail())) {
			Optional<Customer> customer= customerRepository.findByUserId(user.get().getUserId());
				customer.get().setCustomerName(customerRegistrationRequest.getCustomerName());
				customer.get().setCity(customerRegistrationRequest.getCity());
				customer.get().setContactNo(customerRegistrationRequest.getContactNo());;
				customer.get().setUserId(user.get().getUserId());
				customerRepository.save(customer.get());
		}else {
		Customer customer = new Customer();
		customer.setCustomerName(customerRegistrationRequest.getCustomerName());
		customer.setCity(customerRegistrationRequest.getCity());
		customer.setContactNo(customerRegistrationRequest.getContactNo());;
		customer.setUserId(user.get().getUserId());
		customerRepository.save(customer);
		}
		
	}
	
	public Customer getLoggedCustomer(){
		UserDetails loggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user =  userRepository.findByEmail(loggedUser.getUsername());
		return customerRepository.findByUserId(user.get().getUserId()).get();
	}

	public List<Restaurant> showRestaurant(String city, String type,int page) {
		int size=3;
		int lower=((page-1)*size)+1;
		int upper=((page)*size);
		Optional<List<Restaurant>> listOfRestaurant =restoAdminRepository.showRestaurantsWithPaging(city, lower, upper);
		return listOfRestaurant.get();
	}
}
