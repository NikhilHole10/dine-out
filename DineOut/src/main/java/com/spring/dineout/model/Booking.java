package com.spring.dineout.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Long restoId;
	private Long customerId;
	private Long slotId;
	private int bookedSeats;
	private String guestName;
	private Date bookedDate;
	private boolean bookedStatus;
	private Instant createdDate;
	
	
	
	
	
}
