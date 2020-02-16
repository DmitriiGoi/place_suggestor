package com.example.place_suggestor.jobs;


import com.example.place_suggestor.io.CassandraIo;
import com.example.place_suggestor.io.UserRoutesReader;
import com.example.place_suggestor.model.Place;
import com.example.place_suggestor.model.UserRoute;
import com.example.place_suggestor.model.UserVisit;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import static com.example.place_suggestor.util.CoordinatCalculator.ALLOWED_LATITUDE_DELTA_IN_DEGREES;
import static com.example.place_suggestor.util.CoordinatCalculator.ALLOWED_LONGITUDE_DELTA_IN_DEGREES;

public class RecognizeUserRoutePlacesJob implements Job {
    private final JavaSparkContext sparkContext;
    private final Logger logger = Logger.getLogger(RecognizeUserRoutePlacesJob.class);
    private final CassandraIo<UserRoute> userRouteCassandraIo;
    private final CassandraIo<UserVisit> userVisitCassandraIo;
    private final CassandraIo<Place> placeCassandraIo;

    @Inject
    public RecognizeUserRoutePlacesJob(JavaSparkContext sparkContext, CassandraIo<UserRoute> userRouteCassandraIo,
                                       CassandraIo<UserVisit> userVisitCassandraIo,
                                       CassandraIo<Place> placeCassandraIo) {
        this.sparkContext = sparkContext;
        this.userRouteCassandraIo = userRouteCassandraIo;
        this.userVisitCassandraIo = userVisitCassandraIo;
        this.placeCassandraIo = placeCassandraIo;
    }

    @Override
    public void execute() {
        UserRoutesReader routesIo = new UserRoutesReader();
        routesIo.setSparkContext(sparkContext);
        JavaRDD<UserRoute> userRoutes = routesIo.readInput();
        saveToCassandra(userRoutes);


        JavaRDD<UserRoute> userRouteJavaRDD = userRouteCassandraIo.readInput();
        userRouteJavaRDD.foreach(userRoute -> {
            JavaRDD<Place> placeJavaRDD = placeCassandraIo.readInputWhere("latitude between :from_lat and :to_lat " +
                            "and longitude between :from_long and :to_long",
                    String.valueOf(userRoute.getLatitude() - ALLOWED_LATITUDE_DELTA_IN_DEGREES),
                    String.valueOf(userRoute.getLatitude() + ALLOWED_LATITUDE_DELTA_IN_DEGREES),
                    String.valueOf(userRoute.getLongitude() - ALLOWED_LONGITUDE_DELTA_IN_DEGREES),
                    String.valueOf(userRoute.getLongitude() + ALLOWED_LONGITUDE_DELTA_IN_DEGREES)
            );

            JavaRDD<UserVisit> userVisits = placeJavaRDD.map(
                    place -> new UserVisit(userRoute.getUserId(), userRoute.getVisitDateTime(), place.getPlaceId()));
            userVisitCassandraIo.writeOutput(userVisits);
        });
    }

    private void saveToCassandra(JavaRDD<UserRoute> rdd) {
        logger.info("Start saving data to cassandra");
        userRouteCassandraIo.writeOutput(rdd);
        logger.info("Done saving to cassandra");
    }

    @Override
    public String getName() {
        return "user_routes.recognize";
    }
}
