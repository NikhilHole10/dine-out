package com.spring.dineout.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.CustomerRegistrationRequest;
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
}
