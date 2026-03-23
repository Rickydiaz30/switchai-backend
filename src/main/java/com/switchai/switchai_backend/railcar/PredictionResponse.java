package com.switchai.switchai_backend.railcar;

public class PredictionResponse {

    private String railcarId;
    private String predictedTrack;

    public PredictionResponse(String railcarId, String predictedTrack) {
        this.railcarId = railcarId;
        this.predictedTrack = predictedTrack;
    }

    public String getRailcarId() {
        return railcarId;
    }

    public String getPredictedTrack() {
        return predictedTrack;
    }
}