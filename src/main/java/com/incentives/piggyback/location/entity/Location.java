package com.incentives.piggyback.location.entity;

import org.springframework.hateoas.ResourceSupport;

public class Location extends ResourceSupport {

	private String userId;
	private double latitude;
	private double longitude;
	private double gpsAccuracy;
	private String deviceId;
	
	public String getUserId() {
		return userId;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getGpsAccuracy() {
		return gpsAccuracy;
	}
	public String getDeviceId() {
		return deviceId;
	}

	@Override
	public String toString() {
		return "Location [userId=" + userId + ", latitude=" + latitude + ", longitude=" + longitude + ", gpsAccuracy="
				+ gpsAccuracy + ", deviceId=" + deviceId + "]";
	}
}