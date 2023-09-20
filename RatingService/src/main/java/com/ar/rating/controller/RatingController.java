package com.ar.rating.controller;

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

import com.ar.rating.entities.Rating;
import com.ar.rating.service.IRatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	private IRatingService ratingService;
	//create rating
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating createRating = ratingService.createRating(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(createRating);
	}
	
	//get all ratings
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
	}
	
	//getRating by userid
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByUseraId(userId));
	}
	
	//getRating by hotelid
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByHotelId(hotelId));
	}
	
}
