package com.example.place_suggestor.jobs;

import com.example.place_suggestor.io.CassandraIo;
import com.example.place_suggestor.model.UserRecommendations;
import com.example.place_suggestor.model.UserVisit;
import com.example.place_suggestor.spark.RecommendationEngine;
import com.example.place_suggestor.spark.als.TrainConfig;
import com.example.place_suggestor.spark.als.TrainedModel;
import com.google.inject.Inject;

public class SaveUserRecommendationsJob implements Job {
    private RecommendationEngine recommendationEngine;
    private CassandraIo<UserVisit> userVisitCassandraIo;

    @Inject
    public SaveUserRecommendationsJob(RecommendationEngine recommendationEngine, CassandraIo<UserVisit> userVisitCassandraIo) {
        this.recommendationEngine = recommendationEngine;
        this.userVisitCassandraIo = userVisitCassandraIo;
    }

    @Override
    public void execute() {
        TrainConfig trainConfig = new TrainConfig(10, 4);
        TrainedModel model = recommendationEngine.train(trainConfig, userVisitCassandraIo);
        CassandraIo<UserRecommendations> recommendationsIo = new CassandraIo<>(UserRecommendations.class, "dev", "user_recommendations");
        recommendationEngine.saveUserRecommendations(model, recommendationsIo);
    }

    @Override
    public String getName() {
        return "user_recommendations.create";
    }
}
