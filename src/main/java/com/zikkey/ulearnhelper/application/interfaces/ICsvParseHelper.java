package com.zikkey.ulearnhelper.application.interfaces;

import com.zikkey.ulearnhelper.application.models.csv.CsvCourse;

import java.io.IOException;

public interface ICsvParseHelper {
    void parseCsvToModel(CsvCourse course, String csvPath) throws IOException;
}
