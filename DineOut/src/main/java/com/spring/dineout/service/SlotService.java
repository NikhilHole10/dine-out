package com.spring.dineout.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dineout.dto.SlotsRequest;
import com.spring.dineout.exception.DineOutException;
import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.Slots;
import com.spring.dineout.repository.SlotRepository;
import com.spring.dineout.wrapper.SlotWrapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SlotService {

	private final SlotRepository slotRepository;
	private final AuthService authService;

	public void addAllSlots(SlotWrapper slotList) {

		List<SlotsRequest> listSlot = slotList.getSlotList();
		System.out.println(listSlot);
		Long restoId= authService.getCurrentRestoAdminUser().get().getRestoId();
		for(SlotsRequest slot: listSlot) {
			Slots slots = new Slots();
			if(checkSlotAvailability(restoId,slot.getSlotDate(),slot.getSlotName())) 
			{
				slots.setRestoId(restoId);
				slots.setCapacity(slot.getCapacity());
				slots.setTimeSlot(slot.getSlotName());
				Date slotDate = null;
				try {
					slotDate = new SimpleDateFormat("yyyy-MM-dd").parse(slot.getSlotDate());
				} catch (ParseException e) 
				{
					e.printStackTrace();
				}
				slots.setSlotDate(slotDate);

				slotRepository.save(slots);
			}
			else {
				throw new DineOutException("This slot already exists");
			}	
		}
		
	}


	public boolean checkSlotAvailability(Long restoId,String slotDate, String slotName) {

		Optional<Slots> slot = slotRepository.checkSlots(restoId,slotDate+" 00:00:00", slotName);
		if (slot.isEmpty()) {

			return true;
		}
		else {

			return false;
		}
	}



}
