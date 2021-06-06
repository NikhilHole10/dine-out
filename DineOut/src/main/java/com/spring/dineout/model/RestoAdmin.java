package com.spring.dineout.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
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
public class RestoAdmin implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restaurantId;
	private String hotelName;
	private String ownerName;
	private String email;
	private String contactNo;
	private String password;
	private String city;
	private Instant openingTime;
	private Instant closingTime;
	private Integer totalSeats;
	private boolean restoStatus;
	private boolean accountStatus;
	private boolean adminVerified;
	private Instant createdDate;
	private Instant isDeleted;
	private RoleEnum roleEnum;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 SimpleGrantedAuthority authority =  new SimpleGrantedAuthority(roleEnum.name());
			return Collections.singleton(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return accountStatus;
	}

	public RestoAdmin(String hotelName, String ownerName, String email, String contactNo, String password, String city,
			Instant openingTime, Instant closingTime, Integer totalSeats, boolean restoStatus, boolean accountStatus,
			boolean adminVerified, Instant createdDate, Instant isDeleted, RoleEnum roleEnum) {
		super();
		this.hotelName = hotelName;
		this.ownerName = ownerName;
		this.email = email;
		this.contactNo = contactNo;
		this.password = password;
		this.city = city;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.totalSeats = totalSeats;
		this.restoStatus = restoStatus;
		this.accountStatus = accountStatus;
		this.adminVerified = adminVerified;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
		this.roleEnum = roleEnum;
	}

	
	
	
}
