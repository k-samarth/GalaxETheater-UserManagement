package com.galaxeTheater.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxeTheater.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findByCode(String code);

	public User save(Optional<User> users);
}
