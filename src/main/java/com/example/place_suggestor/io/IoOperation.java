package com.example.place_suggestor.io;

import org.apache.spark.api.java.JavaRDD;

public interface IoOperation<T> {
    JavaRDD<T> readInputWhere(String condition, String... args);

    JavaRDD<T> readInput();

    void writeOutput(JavaRDD<T> javaRDD);
}