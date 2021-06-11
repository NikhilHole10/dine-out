package com.spring.dineout.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dineout.dto.SlotsRequest;
import com.spring.dineout.service.SlotService;
import com.spring.dineout.wrapper.SlotWrapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/slot")
public class SlotController {
		
	private final SlotService slotService;
	
	@PostMapping("/addSlots")
	@PreAuthorize("hasAuthority('RESTOADMIN')")
	public void createSlots(@RequestBody SlotWrapper slotList) {
		//System.out.println(slotList);
		slotService.addAllSlots(slotList);
	}
}
