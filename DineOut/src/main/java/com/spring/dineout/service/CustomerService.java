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
		List<Restaurant> restolist=null;
		System.out.println(type);
		if(type.toLowerCase().compareTo("new")==0) {
			System.out.println("new");
			restolist= showRestaurantByNew(city, type,lower,upper);
		}
		else if(type.toLowerCase().compareTo("popularity")==0) {
			System.out.println("populariy");
			restolist = showRestaurantByPopularity(city, type, lower, upper);
		}
		
		return restolist;
		
	}
	
	public List<Restaurant> showRestaurantByNew(String city,String type,int lower,int upper){
		Optional<List<Restaurant>> listOfRestaurant =restoAdminRepository.showNewRestaurantsWithPaging(city, lower, upper);
		return listOfRestaurant.get();
	}
	
	public List<Restaurant> showRestaurantByPopularity(String city,String type,int lower,int upper){
		Optional<List<Restaurant>> listOfRestaurant =restoAdminRepository.showPopularRestaurantsWithPaging(city, lower, upper);
		return listOfRestaurant.get();
	}
}
