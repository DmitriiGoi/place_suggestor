package com.example.place_suggestor.util;

public class CoordinatCalculator {
    private static final Double ALLOWED_DELTA_IN_METERS = Double.parseDouble(PropertyReader.getPropertyValue("range_delta.meters"));
    private static final Double METRES_IN_ONE_DEGREE_LONGITUDE = 111321.377778;
    public static final Double ALLOWED_LONGITUDE_DELTA_IN_DEGREES = ALLOWED_DELTA_IN_METERS / METRES_IN_ONE_DEGREE_LONGITUDE;
    private static final Double METRES_IN_ONE_DEGREE_LATITUDE = 111134.861111;
    public static final Double ALLOWED_LATITUDE_DELTA_IN_DEGREES = ALLOWED_DELTA_IN_METERS / METRES_IN_ONE_DEGREE_LATITUDE;

}

