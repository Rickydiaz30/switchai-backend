package com.switchai.switchai_backend.railcar;

import com.switchai.switchai_backend.track.Track;
import com.switchai.switchai_backend.track.TrackService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RailcarService {

    private final RailcarRepository repository;
    private final RestTemplate restTemplate;
    private final ClassificationRepository classificationRepository;
    private final TrackService trackService;

    public RailcarService(RailcarRepository repository, RestTemplate restTemplate, ClassificationRepository classificationRepository, TrackService trackService) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.classificationRepository = classificationRepository;
        this.trackService = trackService;
    }

    public Railcar save(Railcar railcar) {
        railcar.setDestination(railcar.getDestination().trim().toUpperCase());
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

        Integer mlTrack = restTemplate.postForObject(url, railcar, Integer.class);

// 1. Get valid tracks
        List<Track> validTracks = trackService.findValidTracks(railcar);

        System.out.println("Valid tracks count: " + validTracks.size());
        System.out.println("ML suggested track: " + mlTrack);

// 2. Try to match ML result
        Track assignedTrack = validTracks.stream()
                .filter(t -> t.getTrackNumber().equals(String.valueOf(mlTrack)))
                .findFirst()
                .orElse(null);

// 3. Fallback logic
        if (assignedTrack == null) {
            System.out.println("ML track not valid, using fallback");

            if (!validTracks.isEmpty()) {
                assignedTrack = validTracks.get(0);
            } else {
                throw new RuntimeException("No valid tracks available");
            }
        }

// 4. Save classification
        String recommendedTrack = String.valueOf(mlTrack);
        String assignedTrackNumber = assignedTrack.getTrackNumber();

        String assignmentSource = recommendedTrack.equals(assignedTrackNumber)
                ? "ML"
                : "FALLBACK";

        Classification classification = new Classification(
                railcar.getId(),
                recommendedTrack,
                assignedTrackNumber,
                assignmentSource
        );

        classificationRepository.save(classification);

// 5. Return response
        return new PredictionResponse(
                railcar.getId(),
                assignedTrack.getTrackNumber()
        );
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