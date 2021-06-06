package com.spring.dineout.model;

import java.time.Instant;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
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
	private Long user_id;
	private String name;
	private String email;
	private String contact_no;
	private String password;
	private String city;
	private Instant createdDate;
	private boolean account_status;
	private boolean isDeleted;
	private Role_Enum role_Enum;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
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
			boolean account_status, boolean isDeleted, Role_Enum role_Enum) {
		super();
		this.name = name;
		this.email = email;
		this.contact_no = contact_no;
		this.password = password;
		this.city = city;
		this.createdDate = createdDate;
		this.account_status = account_status;
		this.isDeleted = isDeleted;
		this.role_Enum = role_Enum;
	}
	
}
