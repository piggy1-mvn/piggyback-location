package com.incentives.piggyback.location.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.incentives.piggyback.location.adapter.ObjectAdapter;
import com.incentives.piggyback.location.dto.LocationEntity;
import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.ExceptionResponseCode;
import com.incentives.piggyback.location.exception.PiggyException;
import com.incentives.piggyback.location.publisher.LocationEventPublisher;
import com.incentives.piggyback.location.repository.LocationRepository;
import com.incentives.piggyback.location.service.LocationService;
import com.incentives.piggyback.location.utils.CommonUtility;
import com.incentives.piggyback.location.utils.Constant;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationEventPublisher.PubsubOutboundGateway messagingGateway;

	Gson gson = new Gson();

	@Override
	public String saveLocationCoordinates(Location location) {
		validateLocationParameters(location);
		//save in database
		List<LocationEntity> existingDetails = locationRepository.findByUserId(location.getUserId());
		if (CommonUtility.isValidList(existingDetails)) {
			LocationEntity existingData = existingDetails.get(0);
			LocationEntity response = locationRepository.save(ObjectAdapter.updateLocationEntity(existingData, location));
			publishLocationUpdates(response, Constant.LOCATION_UPDATED_EVENT);
		} else {
			LocationEntity response = locationRepository.save(ObjectAdapter.getLocationEntity(location));
			publishLocationUpdates(response, Constant.LOCATION_CREATED_EVENT);
		}
		return "Location updated successfully!";
	}

	@Override
	public List<String> getNearbyUsers(String userId, double latitude, double longitude, double optimizedRadius) {
		Point point = new Point(latitude, longitude);
		Circle circle = new Circle(point, new Distance(optimizedRadius, Metrics.KILOMETERS));
		List<LocationEntity> nearbyLocations = locationRepository.findByUserIdNotAndLocationWithin
				(userId, circle);
		List<String> userIds = new ArrayList<String>();
		nearbyLocations.forEach(location-> {
			userIds.add(location.getUserId());
		});
		return userIds;
	}

	private void validateLocationParameters(Location location) {
		if (!(CommonUtility.isValidDouble(location.getLatitude()) && CommonUtility.isValidDouble(location.getLongitude())
				&& CommonUtility.isValidString(location.getUserId())))
			throw new PiggyException(ExceptionResponseCode.USER_DATA_NOT_FOUND_IN_REQUEST);
	}

	private void publishLocationUpdates(LocationEntity location, String eventType) {
		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						gson.toJson(location),
						eventType,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.LOCATION_SOURCE_ID
						));
	}

}