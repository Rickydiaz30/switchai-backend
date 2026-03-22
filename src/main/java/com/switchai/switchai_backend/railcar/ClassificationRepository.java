package com.switchai.switchai_backend.railcar;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassificationRepository extends MongoRepository<Classification, String> {
    List<Classification> findByRailcarId(String railcarId);
}