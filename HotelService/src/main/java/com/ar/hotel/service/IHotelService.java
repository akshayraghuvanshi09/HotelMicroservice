package com.ar.hotel.service;

import java.util.List;

import com.ar.hotel.entities.Hotel;

public interface IHotelService {

	// create
	Hotel createHotel(Hotel hotel);

	// getAll
	List<Hotel> getAllHotel();

	// getSingle
	Hotel getHotel(String id);

}
