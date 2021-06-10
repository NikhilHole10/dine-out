package com.spring.dineout.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long restoId;
	private Long userId;
	private String restoName;
	private String ownerName;
	private String contactNo;
	private String city;
	private String address;
	private String mealType;
	private boolean restoStatus;
	
	
}
