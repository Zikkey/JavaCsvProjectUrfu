package com.metanit.application.interfaces;

import com.metanit.application.models.csv.CsvCourse;

import java.io.IOException;

public interface ICsvParseHelper {
    void parseCsvToModel(CsvCourse course, String csvPath) throws IOException;
}
