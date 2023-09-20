package com.ar.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
