package com.switchai.switchai_backend.railcar;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RailcarRepository extends MongoRepository<Railcar, String> {

    List<Railcar> findByDestinationAndTrainId(String destination, String trainId);

    List<Railcar> findByDestination(String destination);
}
