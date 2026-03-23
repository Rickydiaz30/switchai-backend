package com.switchai.switchai_backend.track;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Track {

    @Id
    private String id;
    private String trackNumber;
    private int lengthFeet;
    private int usedLengthFeet;
    private List<String> allowedDestinations;
    private String status;

    public Track() {};

    public Track(String id, String trackNumber, int lengthFeet, int usedLengthFeet, List<String> allowedDestinations, String status) {
        this.id = id;
        this.trackNumber = trackNumber;
        this.lengthFeet = lengthFeet;
        this.usedLengthFeet = usedLengthFeet;
        this.allowedDestinations = allowedDestinations;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getLengthFeet() {
        return lengthFeet;
    }

    public void setLengthFeet(int lengthFeet) {
        this.lengthFeet = lengthFeet;
    }

    public int getUsedLengthFeet() {
        return usedLengthFeet;
    }

    public void setUsedLengthFeet(int usedLengthFeet) {
        this.usedLengthFeet = usedLengthFeet;
    }

    public List<String> getAllowedDestinations() {
        return allowedDestinations;
    }

    public void setAllowedDestinations(List<String> allowedDestinations) {
        this.allowedDestinations = allowedDestinations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", trackNumber='" + trackNumber + '\'' +
                ", lengthFeet=" + lengthFeet +
                ", usedLengthFeet=" + usedLengthFeet +
                ", allowedDestinations=" + allowedDestinations +
                ", status='" + status + '\'' +
                '}';
    }
}
