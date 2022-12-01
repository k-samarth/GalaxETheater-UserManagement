package com.galaxeTheater.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxeTheater.entities.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long>{
	public Optional<ImageModel> findByName(String name);
}
