package com.spring.dineout.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class Reviews {
	
	@GetMapping("/{restoid}")
	@PreAuthorize("hasAuthority('RESTOADMIN')")
	public void getReviewByRestoId(@PathVariable Long restoid) {
		
	}
	
}
