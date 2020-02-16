package com.example.place_suggestor.io;

import com.example.place_suggestor.model.Place;
import com.example.place_suggestor.spark.SparkContextAware;
import com.example.place_suggestor.util.PropertyReader;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.time.LocalDate;

import static com.example.place_suggestor.util.DateUtils.DATE_FORMAT;

public class PlacesReader implements SparkContextAware {
    private static final String PLACES_FILE_PATH = PropertyReader.getPropertyValue("places_file.path");

    private JavaSparkContext sparkContext;

    @Override
    public void setSparkContext(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    public JavaRDD<Place> readInput() {
        JavaRDD<String> data = sparkContext.textFile(PLACES_FILE_PATH);

        return data.map(line -> {
            String[] lineParts = line.split(",");
            int placeId = Integer.parseInt(lineParts[0]);
            String placeName = lineParts[1];
            String placeCategory = lineParts[2];
            String placeDescription = lineParts[3];
            Double latitude = Double.parseDouble(lineParts[4]);
            Double longitude = Double.parseDouble(lineParts[5]);
            int areaId = Integer.parseInt(lineParts[6]);
            LocalDate firstDayOfDataMonth = LocalDate.from(DATE_FORMAT.parse(lineParts[7]));

            return new Place(placeId, placeName, placeCategory, placeDescription, latitude, longitude, areaId, firstDayOfDataMonth);
        });
    }

}
