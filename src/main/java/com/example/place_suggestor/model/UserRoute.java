package com.example.place_suggestor.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRoute {
    private Integer userId;
    private LocalDateTime visitDateTime;
    private Double latitude;
    private Double longitude;
    private Integer areaId;
    private LocalDate startOfMonth;

    public UserRoute(Integer userId, LocalDateTime visitDateTime, Double latitude, Double longitude, Integer areaId,
                     LocalDate startOfMonth) {
        this.userId = userId;
        this.visitDateTime = visitDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areaId = areaId;
        this.startOfMonth = startOfMonth;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserRoute setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public UserRoute setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public UserRoute setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public UserRoute setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public UserRoute setAreaId(Integer areaId) {
        this.areaId = areaId;
        return this;
    }
}
