package com.spring.dineout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationRequest {
	private String customerName;
	private String contactNo;
	private String city;
}
