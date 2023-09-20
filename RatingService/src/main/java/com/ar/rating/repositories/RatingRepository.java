package com.ar.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ar.rating.entities.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
	// custom finder method
	List<Rating> findByUserId(String userId);

	List<Rating> findByHotelId(String hotelId);

}
