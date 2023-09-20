package com.ar.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.hotel.entities.Hotel;

public interface HotelRepositories extends JpaRepository<Hotel,String> {

}
