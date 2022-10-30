package com.metanit;

import com.metanit.domain.helpers.CsvParseHelper;
import com.metanit.domain.models.CsvCourse;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        var course = new CsvCourse("BasicProgramming", new ArrayList<>(), new ArrayList<>());
        CsvParseHelper.parseCsvToModel(course, "basicprogramming_2.csv");
    }
}
