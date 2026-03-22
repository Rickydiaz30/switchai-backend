package com.switchai.switchai_backend.railcar;

public class PredictionResponse {

    private String railcarId;
    private int predictedTrack;

    public PredictionResponse(String railcarId, int predictedTrack) {
        this.railcarId = railcarId;
        this.predictedTrack = predictedTrack;
    }

    public String getRailcarId() {
        return railcarId;
    }

    public int getPredictedTrack() {
        return predictedTrack;
    }
}