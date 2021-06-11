package com.spring.dineout.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotRegisterRequest {
	private Long restoid;
	private String slotDate;
	private String time;
	private int capacity;

}
