package com.galaxeTheater.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxeTheater.entities.ImageModel;
import com.galaxeTheater.repository.ImageRepository;
import com.galaxeTheater.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public ImageModel saveImage(ImageModel image) {
		return imageRepository.save(image);
	}



}
