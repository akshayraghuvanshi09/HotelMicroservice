package com.ar.rating.service;

import java.util.List;

import com.ar.rating.entities.Rating;

public interface IRatingService {
	// create
	Rating createRating(Rating rating);

	// get all ratings
	List<Rating> getAllRatings();

	// get all rating by user id
	List<Rating> getRatingsByUseraId(String userId);

	// get all ratings by hotel
	List<Rating> getRatingsByHotelId(String hotelId);

}
