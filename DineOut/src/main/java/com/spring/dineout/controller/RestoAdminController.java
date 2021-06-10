package com.spring.dineout.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.RestoAdminRegisterRequest;
import com.spring.dineout.model.Booking;
import com.spring.dineout.service.RestaurantAdminService;
import lombok.AllArgsConstructor;
import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/restaurant")
public class RestoAdminController {
	
	private final RestaurantAdminService restaurantAdminService ;
	
	
	@GetMapping("/pendingapprovals")
	@PreAuthorize("hasAuthority('RESTOADMIN')")
	public List<Booking> pendingApprovals() {
		return restaurantAdminService.getPendingApprovals();
	}
	
	@PostMapping("/saverestaurant")
	@PreAuthorize("hasAuthority('RESTOADMIN')")
	public void saveRestaurantDetails(@RequestBody RestoAdminRegisterRequest restoAdminRegisterRequest) {
		restaurantAdminService.saveRestaurantDetails(restoAdminRegisterRequest);
	}
	/*
	 * @GetMapping("/approveorder/{bookingid}")
	 * 
	 * @PreAuthorize("hasAuthority('RESTOADMIN')") public ResponseEntity<Void>
	 * approveOrder(@PathVariable Long bookingid) {
	 * restaurantAdminService.approveOrder(bookingid); return new
	 * ResponseEntity<>(HttpStatus.OK); }
	 */
	
}
