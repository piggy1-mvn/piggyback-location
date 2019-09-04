package com.incentives.piggyback.location.controller;

import com.incentives.piggyback.common.pubsub.TopicHelper;
import com.incentives.piggyback.location.publisher.LocationEventPublisher;
import com.incentives.piggyback.location.utils.CommonUtility;
import com.incentives.piggyback.location.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.PiggyException;
import com.incentives.piggyback.location.service.LocationService;
import com.incentives.piggyback.location.utils.RestResponse;
import com.incentives.piggyback.location.utils.RestUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@RestController
public class LocationController {

	private static final Log LOGGER = LogFactory.getLog(LocationController.class);

	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationEventPublisher.PubsubOutboundGateway messagingGateway;

	@RequestMapping(value="/location")
	@PostMapping
	public ResponseEntity<RestResponse<String>> saveLocationCoordinates(@RequestBody 
			Location location) throws PiggyException {

		ResponseEntity<RestResponse<String>> response =
				RestUtils.successResponse(locationService.saveLocationCoordinates(location));

		try {
			if(!TopicHelper.checkTopicExists(Constant.LOCATION_PUBLISHER_TOPIC)) {
				TopicHelper.createTopic(Constant.LOCATION_PUBLISHER_TOPIC);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

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

}