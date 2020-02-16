package com.example.place_suggestor.model;

import java.time.LocalDateTime;

public class UserVisit {
    private Integer userId;
    private LocalDateTime visitDateTime;
    private Integer placeId;

    public UserVisit(Integer userId, LocalDateTime visitDateTime, Integer placeId) {
        this.userId = userId;
        this.visitDateTime = visitDateTime;
        this.placeId = placeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserVisit setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getVisitDateTime() {
        return visitDateTime;
    }

    public UserVisit setVisitDateTime(LocalDateTime visitDateTime) {
        this.visitDateTime = visitDateTime;
        return this;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public UserVisit setPlaceId(Integer placeId) {
        this.placeId = placeId;
        return this;
    }

    public boolean equals(Object o) {
        if (o instanceof UserVisit) {
            UserVisit userVisit = (UserVisit) o;
            return this.getUserId().equals(userVisit.getUserId()) && this.getPlaceId().equals(userVisit.getPlaceId());
        } else {
            return false;
        }
    }
}
