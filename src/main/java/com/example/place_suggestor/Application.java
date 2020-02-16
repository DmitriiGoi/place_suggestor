package com.example.place_suggestor;

import com.example.place_suggestor.di.MainModule;
import com.example.place_suggestor.di.SparkModule;
import com.example.place_suggestor.jobs.JobExecutor;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
    public static void main(String[] arguments) {
        Injector injector = Guice.createInjector(new MainModule(), new SparkModule());

        JobExecutor executor = injector.getInstance(JobExecutor.class);
        executor.execute("places.import");
        executor.execute("user_routes.import");
        executor.execute("user_routes.recognize");
        executor.execute("user_recommendations.create");
    }

}