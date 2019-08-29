package com.incentives.piggyback.location.entity;

import lombok.Data;

@Data
public class Location {

	private Long userId;
	private Double latitude;
	private Double longitude;
	private Double gpsAccuracy;
	private String deviceId;
}