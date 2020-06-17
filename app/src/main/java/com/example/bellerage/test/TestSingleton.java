package com.example.bellerage.test;

public class TestSingleton {
    static String instance = null;

    public static String getInstance() {
        if (instance == null) {
            instance = "testSingleton";
        }

        return instance;
    }
}
