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

        List<Track> tracks = trackRepository.findAll();
        System.out.println("Total tracks: " + tracks.size());

        return tracks.stream()
                .filter(track -> {

                    System.out.println("----");
                    System.out.println("Checking track: " + track.getTrackNumber());

                    boolean statusOk = "AVAILABLE".equalsIgnoreCase(track.getStatus());
                    System.out.println("Status OK: " + statusOk);

                    boolean destOk = track.getAllowedDestinations() != null &&
                            track.getAllowedDestinations().stream()
                                    .anyMatch(dest ->
                                            dest != null &&
                                                    railcar.getDestination() != null &&
                                                    dest.trim().equalsIgnoreCase(railcar.getDestination().trim())
                                    );
                    System.out.println("Destination OK: " + destOk);

                    boolean fits = track.getUsedLengthFeet() + (int) railcar.getLength() <= track.getLengthFeet();
                    System.out.println("Fits capacity: " + fits);

                    boolean result = statusOk && destOk && fits;
                    System.out.println("FINAL: " + result);

                    return result;
                })
                .toList();
    }
}
