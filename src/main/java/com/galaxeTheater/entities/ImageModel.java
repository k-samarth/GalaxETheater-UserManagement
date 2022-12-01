package com.galaxeTheater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ImageModel {

	public ImageModel(byte[] pic, String type, String name) {
		super();
		this.pic = pic;
		this.type = type;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "PicByte", length = 1000)
	private byte[] pic;
	
	private String type;
	
	private String name;

	public ImageModel(byte[] pic) {
		super();
		this.pic = pic;
	}

}
