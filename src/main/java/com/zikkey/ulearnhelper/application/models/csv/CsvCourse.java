package com.zikkey.ulearnhelper.application.models.csv;

import java.util.ArrayList;

public class CsvCourse {
    public String name;
    public ArrayList<CsvModule> modules;
    public ArrayList<CsvStudent> students;

    public CsvCourse(String name) {
        this.name = name;
        this.modules = new ArrayList<>();
        this.students = new ArrayList<>();
    }
}
