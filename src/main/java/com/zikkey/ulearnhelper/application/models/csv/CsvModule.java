package com.zikkey.ulearnhelper.application.models.csv;

import java.util.ArrayList;

public class CsvModule {
    public String name;
    public ArrayList<CsvExercise> exercises;
    public int startPosition;

    public CsvModule(String name, ArrayList<CsvExercise> exercises, int startPosition) {
        this.name = name;
        this.exercises = exercises;
        this.startPosition = startPosition;
    }
}
