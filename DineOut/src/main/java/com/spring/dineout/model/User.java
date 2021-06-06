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
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String email;
	private String contact_no;
	private String password;
	private String city;
	private Instant createdDate;
	private boolean account_status;
	private boolean isDeleted;
	@Enumerated(EnumType.STRING)
	private RoleEnum roleEnum;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority authority =  new SimpleGrantedAuthority(roleEnum.name());
			return Collections.singleton(authority);
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
		return account_status;
	}
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
	
}
