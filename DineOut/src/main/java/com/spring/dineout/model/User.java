package com.spring.dineout.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String hotelName;
	private String email;
	private String contact_no;
	private String password;
	private String city;
	private Instant createdDate;
	private String openingTime;
	private String closingTime;
	private Integer totalSeats;
	private boolean restoStatus;
	private boolean account_status;
	private boolean isDeleted;
	@Enumerated(EnumType.STRING)
	private RoleEnum roleEnum;
		public User(String name, String email, String contact_no, String password, String city, Instant createdDate,
			boolean account_status, boolean isDeleted, String role_Enum) {
		super();
		this.name = name;
		this.email = email;
		this.contact_no = contact_no;
		this.password = password;
		this.city = city;
		this.createdDate = createdDate;
		this.account_status = account_status;
		this.isDeleted = isDeleted;
		this.roleEnum = RoleEnum.valueOf(role_Enum);
	}
		
		public User(String name, String hotelName, String email, String contact_no, String password, String city,
				Instant createdDate, String openingTime, String closingTime, Integer totalSeats, boolean restoStatus,
				boolean account_status, boolean isDeleted, String roleEnum) {
			super();
			this.name = name;
			this.hotelName = hotelName;
			this.email = email;
			this.contact_no = contact_no;
			this.password = password;
			this.city = city;
			this.createdDate = createdDate;
			this.openingTime = openingTime;
			this.closingTime = closingTime;
			this.totalSeats = totalSeats;
			this.restoStatus = restoStatus;
			this.account_status = account_status;
			this.isDeleted = isDeleted;
			this.roleEnum = RoleEnum.valueOf(roleEnum);
		}

	
}
