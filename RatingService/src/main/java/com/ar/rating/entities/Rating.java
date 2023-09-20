package com.ar.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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
@Entity(name = "user_rating")
public class Rating {
	@Id
	private String id;
	private String userId;
	private String hotelId;
	private Integer rating;
	private String feedback; 
	@Transient
	private Hotel hotel;
}
