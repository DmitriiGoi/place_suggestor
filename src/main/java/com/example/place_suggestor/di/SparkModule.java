package com.example.place_suggestor.di;

import com.example.place_suggestor.io.CassandraIo;
import com.example.place_suggestor.model.Place;
import com.example.place_suggestor.model.UserRecommendations;
import com.example.place_suggestor.model.UserRoute;
import com.example.place_suggestor.model.UserVisit;
import com.example.place_suggestor.util.PropertyReader;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class SparkModule extends AbstractModule {

    private static final String SPARK_MASTER = PropertyReader.getPropertyValue("spark.master");
    private static final String CASSANDRA_HOST = PropertyReader.getPropertyValue("cassandra.host");
    private JavaSparkContext sparkContext = null;
    private JavaStreamingContext streamingContext = null;
    private CassandraIo<UserRoute> userRouteCassandraIo = null;
    private CassandraIo<UserVisit> userVisitCassandraIo = null;
    private CassandraIo<UserRecommendations> userRecommendationsCassandraIo = null;
    private CassandraIo<Place> placeCassandraIo = null;

    @Override
    protected void configure() {
        // Turn off unnecessary logging
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
    }


    @Provides
    SparkConf provideSparkConf() {
        return new SparkConf()
//                .setMaster("spark://127.0.1.1:7077")
                .setMaster(SPARK_MASTER)
                .setJars(new String[] {"target/movie-recommender-1.0-SNAPSHOT-jar-with-dependencies.jar"})
                .setAppName("Place suggestion")
                .set("spark.executor.memory", "4g")
                .set("spark.cassandra.connection.host", CASSANDRA_HOST);
    }

    @Provides
    JavaSparkContext providesJavaSparkContext(SparkConf sparkConf) {
        if (sparkContext != null) {
            return sparkContext;
        }
        sparkContext = new JavaSparkContext(sparkConf);

        return sparkContext;
    }

    @Provides
    JavaStreamingContext provideStreamingContext(JavaSparkContext sparkContext) {
        if (streamingContext != null) {
            return streamingContext;
        }
        streamingContext = new JavaStreamingContext(sparkContext, Durations.seconds(5));

        return streamingContext;
    }


    @Provides
    CassandraIo<UserRoute> providesCassandraUserRouteIO(JavaSparkContext sparkContext) {
        if (userRouteCassandraIo != null) {
            return userRouteCassandraIo;
        }
        userRouteCassandraIo = new CassandraIo<>(UserRoute.class, "dev", "user_route");
        userRouteCassandraIo.setSparkContext(sparkContext);


        return userRouteCassandraIo;
    }

    @Provides
    CassandraIo<Place> providesCassandraPlacesIO(JavaSparkContext sparkContext) {
        if (placeCassandraIo != null) {
            return placeCassandraIo;
        }
        placeCassandraIo = new CassandraIo<>(Place.class, "dev", "place");
        placeCassandraIo.setSparkContext(sparkContext);


        return placeCassandraIo;
    }

    @Provides
    CassandraIo<UserVisit> providesCassandraUserVisitIO(JavaSparkContext sparkContext) {
        if (userVisitCassandraIo != null) {
            return userVisitCassandraIo;
        }
        userVisitCassandraIo = new CassandraIo<>(UserVisit.class, "dev", "user_visit");
        userVisitCassandraIo.setSparkContext(sparkContext);


        return userVisitCassandraIo;
    }

    @Provides
    CassandraIo<UserRecommendations> providesCassandraUserRecommendationsIO(JavaSparkContext sparkContext) {
        if (userRecommendationsCassandraIo != null) {
            return userRecommendationsCassandraIo;
        }
        userRecommendationsCassandraIo = new CassandraIo<>(UserRecommendations.class, "dev", "user_recommendation");
        userRecommendationsCassandraIo.setSparkContext(sparkContext);


        return userRecommendationsCassandraIo;
    }
}