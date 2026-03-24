package com.switchai.switchai_backend.railcar;

public class PredictionRequest {

    private String destination;
    private double length;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}