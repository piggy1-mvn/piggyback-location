package com.incentives.piggyback.location.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "location")
public class LocationEntity {
	
	@JsonIgnore
	@Id
	private String _id;
	private Long userId;
	private Double latitude;
	private Double longitude;
	private Double gpsAccuracy;
	private String deviceId;
	private Date createdDate;
	private Date lastModifiedDate;
}