package com.ar.rating.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ar.rating.entities.Hotel;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<Hotel> getHoteById(@PathVariable String hotelId);
}
