package com.ar.user.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	private String id;
	private String userId;
	private String hotelId;
	private Integer rating;
	private String feedback;
	private Hotel hotel;
}
