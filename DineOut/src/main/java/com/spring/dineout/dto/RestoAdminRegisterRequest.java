package com.spring.dineout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestoAdminRegisterRequest {

	private String restoName;
	private String ownerName;
	private String contactNo;
	private String city;
	private String address;
	private String MealType;
}
