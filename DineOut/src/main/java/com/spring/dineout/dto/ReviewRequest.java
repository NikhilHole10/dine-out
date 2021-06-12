package com.spring.dineout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
	private Long reviewId;
	private Long restoId;
	private int ratings;
	private String review;
	private String userName;
	private String createdDate;
}
