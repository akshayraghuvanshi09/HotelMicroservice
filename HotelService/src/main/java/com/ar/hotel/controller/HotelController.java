package com.ar.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.hotel.entities.Hotel;
import com.ar.hotel.service.IHotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private IHotelService hotelService;

	// create
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel createHotel = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
	}

	// getSingle
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHoteById(@PathVariable String hotelId) {
		Hotel hotel = hotelService.getHotel(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(hotel);
	}

	// getAll
	@GetMapping()
	public ResponseEntity<List<Hotel>> getAllHotel() {
		List<Hotel> allHotel = hotelService.getAllHotel();
		return ResponseEntity.status(HttpStatus.OK).body(allHotel);
	}

}
