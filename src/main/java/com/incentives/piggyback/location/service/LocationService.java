package com.incentives.piggyback.location.service;

import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.PiggyException;

public interface LocationService {

	String saveLocationCoordinates(Location location) throws PiggyException;
}