package com.spring.dineout.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	private int ratings;
	@NotEmpty
	private String review;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restoId",referencedColumnName = "restoId")
	private Restaurant restaurant;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customerId",referencedColumnName = "customerId")
	private Customer customer;
}
