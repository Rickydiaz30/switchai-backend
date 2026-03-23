package com.switchai.switchai_backend.track;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public Track createTrack(@RequestBody Track track) {
        return trackService.save(track);
    }

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GetMapping("/{id}")
    public Track getById(@PathVariable String id) {
        return trackService.getById(id);
    }

}
