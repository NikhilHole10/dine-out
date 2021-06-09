package com.spring.dineout.dto;



import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestoAdminRegisterRequest {

	private String hotelName;
	private String ownerName;
	private String email;
	private String contactNo;
	private String password;
	private String city;
	private String openingTime;
	private String closingTime;
	private Integer totalSeats;
}
