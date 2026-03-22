package com.switchai.switchai_backend.railcar;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/railcars")
public class RailcarController {

    private final RailcarService service;


    public RailcarController(RailcarService service) {
        this.service = service;

    }

    @GetMapping("/{id}")
    public Railcar getById(@PathVariable String id) {
        return service.getRailcarById(id);
    }

    @GetMapping
    public List<Railcar> getRailcars(
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String trainId) {

        return service.getFiltered(destination, trainId);
    }

    @GetMapping("/classifications")
    public List<Classification> getClassifications(
            @RequestParam(required = false) String railcarId) {

        if (railcarId != null) {
            return service.getByRailcarId(railcarId);
        }

        return service.getAllClassifications();
    }

    @PostMapping
    public Railcar create(@Valid @RequestBody Railcar railcar) {
        return service.save(railcar);
    }

    @PostMapping("/predict")
    public PredictionResponse predict(@RequestBody PredictionRequest request) {
        return service.predictTrack(request.getRailcarId());
    }
}