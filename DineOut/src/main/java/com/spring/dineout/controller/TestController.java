package com.spring.dineout.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.wrapper.SlotWrapper;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@ResponseBody
	@PostMapping
	public void saveList(@RequestBody SlotWrapper wrapper){
		System.out.println(wrapper);
		}
}
