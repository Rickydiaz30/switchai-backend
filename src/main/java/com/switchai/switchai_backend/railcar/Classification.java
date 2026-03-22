package com.switchai.switchai_backend.railcar;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "classifications")
public class Classification {

    @Id
    private String id;

    private String railcarId;
    private int track;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Classification(String railcarId, int track) {
        this.railcarId = railcarId;
        this.track = track;
    }

    public String getId() {
        return id;
    }

    public String getRailcarId() {
        return railcarId;
    }

    public int getTrack() {
        return track;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}