package com.example.place_suggestor.io;

import com.example.place_suggestor.model.UserRoute;
import com.example.place_suggestor.spark.SparkContextAware;
import com.example.place_suggestor.util.PropertyReader;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.example.place_suggestor.util.DateUtils.DATE_FORMAT;
import static com.example.place_suggestor.util.DateUtils.DATE_TIME_FORMAT;

public class UserRoutesReader implements SparkContextAware {
    private static final String USER_ROUTES_FILE_PATH = PropertyReader.getPropertyValue("user_routes_file.path");

    private JavaSparkContext sparkContext;

    @Override
    public void setSparkContext(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    public JavaRDD<UserRoute> readInput() {
        JavaRDD<String> data = sparkContext.textFile(USER_ROUTES_FILE_PATH);

        return data.map(line -> {
            String[] lineParts = line.split(",");
            int userId = Integer.parseInt(lineParts[0]);
            LocalDateTime localDateTime = LocalDateTime.from(DATE_TIME_FORMAT.parse(lineParts[1]));
            Double latitude = Double.parseDouble(lineParts[2]);
            Double longitude = Double.parseDouble(lineParts[3]);
            int areaId = Integer.parseInt(lineParts[4]);
            LocalDate firstDayOfDataMonth = LocalDate.from(DATE_FORMAT.parse(lineParts[5]));

            return new UserRoute(userId, localDateTime, latitude, longitude, areaId, firstDayOfDataMonth);
        });
    }
}