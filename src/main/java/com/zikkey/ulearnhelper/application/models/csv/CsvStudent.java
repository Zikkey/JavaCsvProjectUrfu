package com.zikkey.ulearnhelper.application.models.csv;

import com.zikkey.ulearnhelper.application.utils.ComposeKey;

import java.util.HashMap;

public class CsvStudent {
    public String name;
    public String groupName;
    public HashMap<ComposeKey<String, String>, Integer> exerciseToScoreDict;

    public CsvStudent(String name, String groupName, HashMap<ComposeKey<String, String>, Integer> exerciseToScoreDict) {
        this.name = name;
        this.groupName = groupName;
        this.exerciseToScoreDict = exerciseToScoreDict;
    }
}
