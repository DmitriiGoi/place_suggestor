package com.example.place_suggestor.di;

import com.example.place_suggestor.jobs.*;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class MainModule extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Job> uriBinder = Multibinder.newSetBinder(binder(), Job.class);
        uriBinder.addBinding().to(ImportUserRouteJob.class);
        uriBinder.addBinding().to(ImportPlaceJob.class);
        uriBinder.addBinding().to(RecognizeUserRoutePlacesJob.class);
        uriBinder.addBinding().to(SaveUserRecommendationsJob.class);
    }
}