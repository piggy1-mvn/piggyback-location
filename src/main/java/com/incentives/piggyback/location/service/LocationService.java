package com.incentives.piggyback.location.service;

import java.util.List;

import com.incentives.piggyback.location.dto.LocationEntity;
import com.incentives.piggyback.location.entity.Location;
import com.incentives.piggyback.location.exception.PiggyException;

public interface LocationService {

	String saveLocationCoordinates(Location location) throws PiggyException;

	List<LocationEntity> getNearbyUsers(Long userId, double latitude, double longitude, Integer page);
}