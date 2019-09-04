package com.incentives.piggyback.location.adapter;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.incentives.piggyback.location.dto.LocationEntity;
import com.incentives.piggyback.location.entity.Location;

public class ObjectAdapter {

	public static LocationEntity getLocationEntity(Location location) {
		LocationEntity locationDTO = new LocationEntity();
		locationDTO.setLocationId(UUID.randomUUID().toString());
		locationDTO.setUserId(location.getUserId());
		double[] points = new double[] {location.getLatitude(), location.getLongitude()};
		locationDTO.setLocation(points);
		locationDTO.setDeviceId(location.getDeviceId());
		locationDTO.setGpsAccuracy(location.getGpsAccuracy());
		locationDTO.setCreatedDate(Calendar.getInstance().getTime());
		locationDTO.setLastModifiedDate(Calendar.getInstance().getTime());
		return locationDTO;
	}

	public static LocationEntity getLocationEntity(Location location, LocationEntity locationDTO) {
		if (location.getGpsAccuracy() > locationDTO.getGpsAccuracy() 
				|| TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis()
						- locationDTO.getLastModifiedDate().getTime()) > 15) {
			double[] points = new double[] {location.getLatitude(), location.getLongitude()};
			locationDTO.setLocation(points);
			locationDTO.setDeviceId(location.getDeviceId());
			locationDTO.setGpsAccuracy(location.getGpsAccuracy());
			locationDTO.setLastModifiedDate(Calendar.getInstance().getTime());
		}
		return locationDTO;
	}
}