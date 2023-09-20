package com.ar.rating.serviceimpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.rating.entities.Rating;
import com.ar.rating.feign.HotelService;
import com.ar.rating.repositories.RatingRepository;
import com.ar.rating.service.IRatingService;

@Service
public class IRatingServiceImpl implements IRatingService {
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private HotelService hotelService;

	@Override
	public Rating createRating(Rating rating) {
		String ratingId = UUID.randomUUID().toString();
		System.out.println(ratingId);
		rating.setId(ratingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		List<Rating> allRatings = ratingRepository.findAll();
		List<Rating> ratings = allRatings.stream().map(rating -> {

			rating.setHotel(hotelService.getHoteById(rating.getHotelId()).getBody());
			return rating;
		}).collect(Collectors.toList());
		return ratings;
	}

	@Override
	public List<Rating> getRatingsByUseraId(String userId) {
		List<Rating> listOfRatings = ratingRepository.findByUserId(userId);

		List<Rating> ratingWithHotel = listOfRatings.stream().map(rating -> {
//			Hotel hotel = hotelService.getHoteById(rating.getHotelId()).getBody();
//			rating.setHotel(hotel);
			rating.setHotel(hotelService.getHoteById(rating.getHotelId()).getBody());
			return rating;
		}).collect(Collectors.toList());
		return ratingWithHotel;
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		List<Rating> ratingsByHotelId = ratingRepository.findByHotelId(hotelId);
		List<Rating> ratingWithHotel = ratingsByHotelId.stream().map(rating -> {
			rating.setHotel(hotelService.getHoteById(hotelId).getBody());
			return rating;
		}).collect(Collectors.toList());

		return ratingWithHotel;
	}

}
