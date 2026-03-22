package com.switchai.switchai_backend.railcar;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RailcarService {

    private final RailcarRepository repository;
    private final RestTemplate restTemplate;
    private final ClassificationRepository classificationRepository;

    public RailcarService(RailcarRepository repository, RestTemplate restTemplate, ClassificationRepository classificationRepository) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.classificationRepository = classificationRepository;
    }

    public Railcar save(Railcar railcar) {
        return repository.save(railcar);
    }

    public List<Railcar> getAll() {
        return repository.findAll();
    }

    public Railcar getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Railcar not found: " + id));
    }

    public PredictionResponse predictTrack(String railcarId) {
        Railcar railcar = repository.findById(railcarId)
                .orElseThrow(() -> new RuntimeException("Railcar not found: " + railcarId));

        String url = "http://localhost:5000/predict";

        Integer result = restTemplate.postForObject(url, railcar, Integer.class);

        Classification classification = new Classification(railcar.getId(), result);
        classificationRepository.save(classification);

        return new PredictionResponse(railcar.getId(), result);
    }

    public Railcar getRailcarById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Railcar not found: " + id));
    }

    public List<Classification> getAllClassifications() {
        return classificationRepository.findAll();
    }

    public List<Railcar> getByDestination(String destination) {
        return repository.findByDestination(destination);
    }

    public List<Classification> getByRailcarId(String railcarId) {
        return classificationRepository.findByRailcarId(railcarId);
    }

    public List<Railcar> getFiltered(String destination, String trainId) {
        if (destination != null && trainId != null) {
            return repository.findByDestinationAndTrainId(destination, trainId);
        } else if (destination != null) {
            return repository.findByDestination(destination);
        } else {
            return repository.findAll();
        }
    }
}