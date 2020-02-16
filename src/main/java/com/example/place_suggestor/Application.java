package com.example.place_suggestor;

import com.example.place_suggestor.di.MainModule;
import com.example.place_suggestor.di.SparkModule;
import com.example.place_suggestor.jobs.*;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
    public static void main(String[] arguments) {
        Injector injector = Guice.createInjector(new MainModule(), new SparkModule());

        JobExecutor executor = injector.getInstance(JobExecutor.class);
        executor.execute(ImportPlaceJob.JOB_NAME);
        executor.execute(ImportUserRouteJob.JOB_NAME);
        executor.execute(RecognizeUserRoutePlacesJob.JOB_NAME);
        executor.execute(SaveUserRecommendationsJob.JOB_NAME);
    }

}