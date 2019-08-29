package com.incentives.piggyback.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.PiggyException;
import com.incentives.piggyback.location.service.LocationService;
import com.incentives.piggyback.location.utils.RestResponse;
import com.incentives.piggyback.location.utils.RestUtils;

@RestController
public class LocationController {

	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/location", method = RequestMethod.POST)
	public ResponseEntity<RestResponse<String>> saveLocationCoordinates(@RequestBody 
			Location location) throws PiggyException {
		return RestUtils.successResponse(locationService.saveLocationCoordinates(location));
	}
}