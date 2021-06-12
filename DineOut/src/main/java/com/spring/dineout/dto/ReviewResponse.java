package com.spring.dineout.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
	private Long reviewId;
	private String review;
	private int ratings;
	private Instant createdDate;
	private String userName;
	private String duration;
}
