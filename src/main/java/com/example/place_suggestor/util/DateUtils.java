package com.example.place_suggestor.util;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(
            PropertyReader.getPropertyValue("date_time_format.pattern"));

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
            PropertyReader.getPropertyValue("date_format.pattern"));

}
