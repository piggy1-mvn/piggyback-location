package com.incentives.piggyback.location.controller;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incentives.piggyback.location.dto.LocationEntity;
import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.PiggyException;
import com.incentives.piggyback.location.publisher.LocationEventPublisher;
import com.incentives.piggyback.location.service.LocationService;
import com.incentives.piggyback.location.utils.CommonUtility;
import com.incentives.piggyback.location.utils.Constant;
import com.incentives.piggyback.location.utils.RestResponse;
import com.incentives.piggyback.location.utils.RestUtils;

@RestController
@RequestMapping(value="/location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationEventPublisher.PubsubOutboundGateway messagingGateway;

	@RequestMapping	@PostMapping
	public ResponseEntity<RestResponse<String>> saveLocationCoordinates(@RequestBody 
			Location location) throws PiggyException {

		ResponseEntity<RestResponse<String>> response =
				RestUtils.successResponse(locationService.saveLocationCoordinates(location));

		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						UUID.randomUUID().toString(),
						Constant.LOCATION_CREATED_EVENT,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.LOCATION_SOURCE_ID
				));

		return response;
	}
	
	@RequestMapping(value="/user")
	@GetMapping
	public ResponseEntity<RestResponse<List<LocationEntity>>> getNearbyUsers(
			@RequestParam("userId") Long userId,
			@RequestParam(value = "latitude") Double latitude,
			@RequestParam(value = "longitude") Double longitude,
			@RequestParam(value = "page", required = false) Integer page
			) throws PiggyException {

		return RestUtils.successResponse(locationService.getNearbyUsers
						(userId, latitude, longitude, page));
	}

}