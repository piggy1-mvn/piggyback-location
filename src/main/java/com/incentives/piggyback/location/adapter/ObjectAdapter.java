package com.incentives.piggyback.location.adapter;

import java.util.Calendar;
import java.util.UUID;

import com.incentives.piggyback.location.dto.LocationEntity;
import com.incentives.piggyback.location.entity.Location;

public class ObjectAdapter {

	public static LocationEntity getLocationEntity(Location location) {
		LocationEntity locationDTO = new LocationEntity();
		locationDTO.setLocationId(UUID.randomUUID().toString());
		locationDTO.setUserId(location.getUserId());
		locationDTO.setLatitude(location.getLatitude());
		locationDTO.setLongitude(location.getLongitude());
		locationDTO.setDeviceId(location.getDeviceId());
		locationDTO.setGpsAccuracy(location.getGpsAccuracy());
		locationDTO.setCreatedDate(Calendar.getInstance().getTime());
		locationDTO.setLastModifiedDate(Calendar.getInstance().getTime());
		return locationDTO;
	}
}
