package com.incentives.piggyback.location.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "location")
public class LocationEntity {
	
	@JsonIgnore
	@Id
	private String locationId;
	private Long userId;
	private Double latitude;
	private Double longitude;
	private Double gpsAccuracy;
	private String deviceId;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public String getLocationId() {
		return locationId;
	}
	public Long getUserId() {
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setGpsAccuracy(Double gpsAccuracy) {
		this.gpsAccuracy = gpsAccuracy;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public String toString() {
		return "LocationEntity [locationId=" + locationId + ", userId=" + userId + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", gpsAccuracy=" + gpsAccuracy + ", deviceId=" + deviceId
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	
}