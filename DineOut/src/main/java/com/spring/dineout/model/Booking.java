package com.spring.dineout.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="userId",referencedColumnName = "userId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurantId",referencedColumnName = "restaurantId")
	private RestoAdmin restoAdmin;
	private String bookedSeats;
	private Instant createdDate;
	private boolean status;
	private Instant bookedDate;
	
	
}
