package com.example.place_suggestor.spark;

import com.example.place_suggestor.io.IoOperation;
import com.example.place_suggestor.model.UserRecommendations;
import com.example.place_suggestor.model.UserVisit;
import com.example.place_suggestor.spark.als.ModelFactory;
import com.example.place_suggestor.spark.als.TrainConfig;
import com.example.place_suggestor.spark.als.TrainedModel;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.recommendation.Rating;
import scala.Tuple2;

import java.util.HashSet;
import java.util.Set;

public class RecommendationEngine {
    private static Logger logger = Logger.getLogger(RecommendationEngine.class);

    public TrainedModel train(TrainConfig trainConfig, IoOperation<UserVisit> ioOperation) {
        logger.info("loadAndParseRatings ratings data");
        JavaRDD<UserVisit> userVisitRDD = ioOperation.readInput();
        JavaRDD<Rating> ratings = userVisitRDD.mapToPair(userVisit -> new Tuple2<>(userVisit, 1L))
                .reduceByKey(Long::sum)
                .map(userVisitNumTuple -> new Rating(userVisitNumTuple._1.getUserId(), userVisitNumTuple._1.getPlaceId(),
                        userVisitNumTuple._2));
        return createAlsModel(ratings, trainConfig);
    }

    private TrainedModel createAlsModel(JavaRDD<Rating> ratings, TrainConfig trainConfig) {
        double[] weights = {8, 2};
        JavaRDD<Rating>[] randomRatings = ratings.randomSplit(weights, 0L);

        return ModelFactory.create(randomRatings[0],
                randomRatings[1],
                trainConfig.getRankNr(),
                trainConfig.getIterationsNr()
        );
    }

    public void saveUserRecommendations(TrainedModel model, IoOperation<UserRecommendations> ioOperation) {
        logger.info("start saving user recommendations");
        JavaRDD<Tuple2<Object, Rating[]>> recommendations = model.getMatrixModel()
                .recommendProductsForUsers(20)
                .toJavaRDD();

        logger.info("recommendations count " + recommendations.count());

        JavaRDD<UserRecommendations> userRecommendationsRDD = recommendations.map(tuple -> {
            Set<Integer> products = new HashSet<>();
            for (Rating rating : tuple._2) {
                products.add(rating.product());
            }

            return new UserRecommendations((int) tuple._1(), products);
        });
        ioOperation.writeOutput(userRecommendationsRDD);
    }
}