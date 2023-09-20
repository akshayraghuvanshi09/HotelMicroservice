package com.ar.user.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ar.user.service.entities.Rating;



@FeignClient("RATING-SERVICE")
public interface RatingService {

	//get
	@GetMapping("/ratings")
	public ResponseEntity<List<Rating>> getAllRatings();
	
	//post
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating);
	
	//get
	@GetMapping("/ratings/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId);
}
