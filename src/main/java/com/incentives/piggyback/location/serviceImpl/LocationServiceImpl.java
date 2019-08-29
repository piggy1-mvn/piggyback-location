package com.incentives.piggyback.location.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incentives.piggyback.location.adapter.ObjectAdapter;
import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.ExceptionResponseCode;
import com.incentives.piggyback.location.exception.PiggyException;
import com.incentives.piggyback.location.repository.LocationRepository;
import com.incentives.piggyback.location.service.LocationService;
import com.incentives.piggyback.location.utils.CommonUtility;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public String saveLocationCoordinates(Location location) {
		validateLocationParameters(location);
		//save in database
		locationRepository.save(ObjectAdapter.getLocationEntity(location));
		return "Location updated successfully!";
	}

	private void validateLocationParameters(Location location) {
		if (!(CommonUtility.isValidDouble(location.getLatitude()) && CommonUtility.isValidDouble(location.getLongitude())
				&& CommonUtility.isValidLong(location.getUserId())))
			throw new PiggyException(ExceptionResponseCode.USER_DATA_NOT_FOUND_IN_REQUEST);
	}
}