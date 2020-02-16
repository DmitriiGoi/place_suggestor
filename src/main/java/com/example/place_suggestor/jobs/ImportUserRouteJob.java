package com.example.place_suggestor.jobs;

import com.example.place_suggestor.io.CassandraIo;
import com.example.place_suggestor.io.UserRoutesReader;
import com.example.place_suggestor.model.UserRoute;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class ImportUserRouteJob implements Job {
    private JavaSparkContext sparkContext;
    private Logger logger = Logger.getLogger(ImportUserRouteJob.class);
    private CassandraIo<UserRoute> userRouteCassandraIo;

    @Inject
    public ImportUserRouteJob(JavaSparkContext sparkContext, CassandraIo<UserRoute> userRouteCassandraIo) {
        this.sparkContext = sparkContext;
        this.userRouteCassandraIo = userRouteCassandraIo;
    }

    @Override
    public void execute() {
        UserRoutesReader routesIo = new UserRoutesReader();
        routesIo.setSparkContext(sparkContext);
        JavaRDD<UserRoute> userRoutes = routesIo.readInput();
        saveToCassandra(userRoutes);
    }

    private void saveToCassandra(JavaRDD<UserRoute> rdd) {
        logger.info("Start saving data to cassandra");
        userRouteCassandraIo.writeOutput(rdd);
        logger.info("Done saving to cassandra");
    }

    @Override
    public String getName() {
        return "user_routes.import";
    }
}