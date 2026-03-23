package com.switchai.switchai_backend.track;

import com.switchai.switchai_backend.railcar.Railcar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    private final TrackRepository trackRepository;


    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track save(Track track) {
        return trackRepository.save(track);
    }

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Track getById(String id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + id));
    }

    public List<Track> findValidTracks(Railcar railcar) {
        return trackRepository.findAll().stream()
                .filter(track -> {

                    boolean statusOk = "AVAILABLE".equalsIgnoreCase(track.getStatus());

                    boolean destOk = track.getAllowedDestinations().stream()
                            .anyMatch(dest ->
                                    dest != null &&
                                            railcar.getDestination() != null &&
                                            dest.trim().equalsIgnoreCase(railcar.getDestination().trim())
                            );

                    boolean fits = track.getUsedLengthFeet() + (int) railcar.getLength() <= track.getLengthFeet();

                    return statusOk && destOk && fits;
                })
                .toList();
    }
}
