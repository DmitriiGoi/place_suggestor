package com.example.place_suggestor.model;

import java.util.Set;

public class UserRecommendations {
    private final Integer user;
    private final Set<Integer> recommendedPlaces;

    public UserRecommendations(Integer user, Set<Integer> recommendedPlaces) {
        this.user = user;
        this.recommendedPlaces = recommendedPlaces;
    }

    public Integer getUser() {
        return user;
    }

    public Set<Integer> getRecommendedPlaces() {
        return recommendedPlaces;
    }
}
