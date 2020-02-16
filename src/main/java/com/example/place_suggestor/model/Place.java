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

    public Place setPlaceId(Integer placeId) {
        this.placeId = placeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Place setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Place setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Place setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Place setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public Place setAreaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }

    public LocalDate getStartOfMonth() {
        return startOfMonth;
    }

    public Place setStartOfMonth(LocalDate startOfMonth) {
        this.startOfMonth = startOfMonth;
        return this;
    }
}
