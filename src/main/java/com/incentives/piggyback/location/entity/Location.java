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
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getGpsAccuracy() {
		return gpsAccuracy;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setGpsAccuracy(double gpsAccuracy) {
		this.gpsAccuracy = gpsAccuracy;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}