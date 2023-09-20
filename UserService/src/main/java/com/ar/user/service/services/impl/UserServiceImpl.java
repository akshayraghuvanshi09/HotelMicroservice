package com.ar.user.service.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ar.user.service.entities.Hotel;
import com.ar.user.service.entities.Rating;
import com.ar.user.service.entities.User;
import com.ar.user.service.exceptions.ResourceNotFoundException;
import com.ar.user.service.feign.HotelService;
import com.ar.user.service.feign.RatingService;
import com.ar.user.service.repositories.UserRepository;
import com.ar.user.service.services.userService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class UserServiceImpl implements userService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// Generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// implementing rating service call: using Rest Template
		List<User> users = repository.findAll();
		List<User> usersWithRating = users.stream().map(user -> {

			user.setRatings(ratingService.getRatingByUserId(user.getUserId()).getBody());
			return user;

		}).collect(Collectors.toList());
		Stream<String> map = users.stream().map(e->e.getName().substring(0,1).toUpperCase().concat(e.getName().substring(1)));
		log.info("user with initial latter capital {}",map);
		
		return usersWithRating;
	}
	int retryCount = 1;
	
	@Override
	//@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback" )
	@RateLimiter(name ="userRateLimiter",fallbackMethod ="ratingHotelFallback" )
	public User getUser(String userId) {
	
//		log.info("Retry Count: {}",retryCount);
//		retryCount++;
		
	
		// get user from database with the help of user repository
		User user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server !!"));

		// fetch rating of the above user from Rating Service
		ResponseEntity<List<Rating>> ratingByUserId = ratingService.getRatingByUserId(user.getUserId());
		List<Rating> listOfRating = ratingByUserId.getBody();

		List<Rating> ratingList = listOfRating.stream().map(rating -> {
			// api call to hotel service to get the hotel
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;
	}
	

	//creating fallback method for circuit breaker
	public User ratingHotelFallback(String userId,Exception ex) {
		//log.info("Fallback is executed because service is down :",ex.getMessage());

		User user = User.builder()
		.email("dummy@gmail.com")
		.name("Dummy")
		.about("This user is created dummy becuse some services is down")
		.userId("14555")
		.build();
		return user;
	}
	
	
	
	
	

}
