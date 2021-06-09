package com.spring.dineout.model;

import java.time.Instant;
import java.util.Date;

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
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name ="userId",referencedColumnName = "userId") private User
	 * user;
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "restaurantId",referencedColumnName = "restaurantId")
	 * private RestoAdmin restoAdmin;
	 */
	private Long userId;
	private Long restoId;
	private int bookedSeats;
	private Instant createdDate;
	private boolean status;
	private Date bookedDate;
	
	public Booking(Long userId, Long restoId, int bookedSeats, Instant createdDate, boolean status,
			Date bookedDate) {
		super();
		this.userId = userId;
		this.restoId = restoId;
		this.bookedSeats = bookedSeats;
		this.createdDate = createdDate;
		this.status = status;
		this.bookedDate = bookedDate;
	}
	
	
	
	
}
