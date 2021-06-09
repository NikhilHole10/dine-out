package com.spring.dineout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestoAdminLoginRequest {
	private String email;
	private String password;
}
