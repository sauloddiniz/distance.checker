package com.distancechecker.service;

public class Tes {
    private static Tes instance;
    private Tes() {}
    public static synchronized Tes getInstance() {
        if (instance == null) {
            instance = new Tes();
        }
        return instance;
    }

}
