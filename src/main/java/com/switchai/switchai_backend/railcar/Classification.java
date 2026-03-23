package com.switchai.switchai_backend.railcar;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "classifications")
public class Classification {



    private String railcarId;
    private String recommendedTrack; // from ML
    private String assignedTrack;    // final decision
    private String assignmentSource; // ML or FALLBACK
    private LocalDateTime timestamp = LocalDateTime.now();

    public Classification(String railcarId,
                          String recommendedTrack,
                          String assignedTrack,
                          String assignmentSource) {
        this.railcarId = railcarId;
        this.recommendedTrack = recommendedTrack;
        this.assignedTrack = assignedTrack;
        this.assignmentSource = assignmentSource;
    }



    public String getRailcarId() {
        return railcarId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getRecommendedTrack() {
        return recommendedTrack;
    }

    public String getAssignedTrack() {
        return assignedTrack;
    }

    public String getAssignmentSource() {
        return assignmentSource;
    }
}