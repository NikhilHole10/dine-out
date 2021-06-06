package com.spring.dineout.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

	private String name;
	private String email;
	private String contact_no;
	private String password;
	private String city;
}
