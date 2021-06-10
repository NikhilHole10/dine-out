package com.spring.dineout.model;

import java.util.Date;

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
public class Slots {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long slotId;
	private Long restoId;
	private Date slotDate;
	private String time;
	private int capacity;
	
}
