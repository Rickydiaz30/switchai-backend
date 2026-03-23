package com.switchai.switchai_backend.railcar;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "railcars")
public class Railcar {
    @Id
    private String id;
    @NotBlank
    private String trainId;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotBlank
    private String carType;
    @Positive
    private double weight;
    @Positive
    private int length;

    private String hazardClass;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Railcar() {}

    public Railcar(String id, String trainId, String origin, String destination, String carType, double weight, int length, String hazardClass, LocalDateTime createdAt) {
        this.id = id;
        this.trainId = trainId;
        this.origin = origin;
        this.destination = destination;
        this.carType = carType;
        this.weight = weight;
        this.length = length;
        this.hazardClass = hazardClass;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getHazardClass() {
        return hazardClass;
    }

    public void setHazardClass(String hazardClass) {
        this.hazardClass = hazardClass;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Railcar{" +
                "id='" + id + '\'' +
                ", trainId='" + trainId + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", carType='" + carType + '\'' +
                ", weight=" + weight +
                ", length=" + length +
                ", hazardClass='" + hazardClass + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}


