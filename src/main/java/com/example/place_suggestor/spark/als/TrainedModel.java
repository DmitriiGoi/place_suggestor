package com.example.place_suggestor.spark.als;

import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;

public class TrainedModel {
    private final Double error;
    private final MatrixFactorizationModel model;

    public TrainedModel(Double error, MatrixFactorizationModel model) {
        this.error = error;
        this.model = model;
    }

    public Double getError() {
        return error;
    }

    public MatrixFactorizationModel getMatrixModel() {
        return model;
    }
}
