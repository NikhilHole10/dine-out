package com.spring.dineout.dto;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotsRequest {
	private String slotDate;
	private String slotName;
	private Integer capacity;
}
