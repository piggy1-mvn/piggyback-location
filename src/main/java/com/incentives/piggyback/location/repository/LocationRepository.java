package com.incentives.piggyback.location.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.location.dto.LocationEntity;

public interface LocationRepository extends MongoRepository<LocationEntity, String> {

	List<LocationEntity> findByUserId(String userId);
	List<LocationEntity> findByUserIdNotAndLocationWithin(String userId, Circle circle, Pageable pageable);
}