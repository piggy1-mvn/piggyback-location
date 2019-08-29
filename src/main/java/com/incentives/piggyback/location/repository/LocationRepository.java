package com.incentives.piggyback.location.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.location.dto.LocationEntity;

public interface LocationRepository extends MongoRepository<LocationEntity, String> {

}