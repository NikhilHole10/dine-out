package com.spring.dineout.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class Test {

	@GetMapping
	public void test() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication()); 
	}
}
