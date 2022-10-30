package com.metanit.domain.models;

import java.util.Dictionary;
import java.util.HashMap;

public class CsvStudent {
    public String name;
    public String groupName;
    public HashMap<String, Integer> exerciseToScoreDict;

    public CsvStudent(String name, String groupName, HashMap<String, Integer> exerciseToScoreDict) {
        this.name = name;
        this.groupName = groupName;
        this.exerciseToScoreDict = exerciseToScoreDict;
    }
}
