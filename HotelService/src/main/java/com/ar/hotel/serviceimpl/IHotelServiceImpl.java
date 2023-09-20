package com.ar.hotel.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.hotel.entities.Hotel;
import com.ar.hotel.exception.ResourceNotFoundException;
import com.ar.hotel.repositories.HotelRepositories;
import com.ar.hotel.service.IHotelService;

@Service	
public class IHotelServiceImpl implements IHotelService {

	@Autowired
	private HotelRepositories hotelRepositories;

	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepositories.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepositories.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepositories.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found !!"));

	}

}
