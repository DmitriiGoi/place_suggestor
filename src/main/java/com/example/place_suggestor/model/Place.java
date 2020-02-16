package com.example.place_suggestor.model;

import java.time.LocalDate;

public class Place {

    private Integer placeId;
    private String name;
    private String category;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer areaId;
    private LocalDate startOfMonth;

    public Place(Integer placeId, String name, String category, String description, Double latitude, Double longitude, Integer areaId,
                 LocalDate startOfMonth) {
        this.placeId = placeId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areaId = areaId;
        this.startOfMonth = startOfMonth;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public LocalDate getStartOfMonth() {
        return startOfMonth;
    }
}
