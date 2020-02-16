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

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
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
