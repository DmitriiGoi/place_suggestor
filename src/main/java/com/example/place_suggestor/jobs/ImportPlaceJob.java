package com.example.place_suggestor.jobs;

import com.example.place_suggestor.io.CassandraIo;
import com.example.place_suggestor.io.PlacesReader;
import com.example.place_suggestor.model.Place;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class ImportPlaceJob implements Job {
    public static final String JOB_NAME = "places.import";
    private JavaSparkContext sparkContext;
    private Logger logger = Logger.getLogger(ImportPlaceJob.class);
    private CassandraIo<Place> placeCassandraIo;

    @Inject
    public ImportPlaceJob(JavaSparkContext sparkContext, CassandraIo<Place> placeCassandraIo) {
        this.sparkContext = sparkContext;
        this.placeCassandraIo = placeCassandraIo;
    }

    @Override
    public void execute() {
        PlacesReader routesIo = new PlacesReader();
        routesIo.setSparkContext(sparkContext);
        JavaRDD<Place> userRoutes = routesIo.readInput();
        saveToCassandra(userRoutes);
    }

    private void saveToCassandra(JavaRDD<Place> rdd) {
        logger.info("Start saving data to cassandra");
        placeCassandraIo.writeOutput(rdd);
        logger.info("Done saving to cassandra");
    }

    @Override
    public String getName() {
        return JOB_NAME;
    }
}