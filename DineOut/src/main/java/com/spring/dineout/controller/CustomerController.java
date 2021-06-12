package com.spring.dineout.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.CustomerRegistrationRequest;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@PostMapping("/savecustomer")
	@PreAuthorize("hasAuthority('USER')")
	public void saveCustomerDetails(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
		customerService.saveCustomerDetails(customerRegistrationRequest);
	}
	
	@PostMapping("/listrestobycity")
	@PreAuthorize("hasAuthority('USER')")
	public void listRestaurantByCity(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
		
	}
	
	
	@GetMapping("/showrestaurants/city={city}&sort={type}&page={page}")
	@PreAuthorize("hasAuthority('USER')")
	public List<Restaurant> showRestaurant(@PathVariable String city ,@PathVariable String type,@PathVariable int page) {
		return customerService.showRestaurant(city,type,page);
	}
	
	
}
