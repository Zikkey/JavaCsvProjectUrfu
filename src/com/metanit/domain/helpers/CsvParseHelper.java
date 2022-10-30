package com.metanit.domain.helpers;

import au.com.bytecode.opencsv.CSVReader;
import com.metanit.domain.extensions.StringExtensions;
import com.metanit.domain.models.CsvCourse;
import com.metanit.domain.models.CsvExercise;
import com.metanit.domain.models.CsvModule;
import com.metanit.domain.models.CsvStudent;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CsvParseHelper {
    private static final int START_POS = 6;
    public static void parseCsvToModel(CsvCourse course, String csvPath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(csvPath), ';', '"');
        var modules = reader.readNext();
        var exercises = reader.readNext();
        var thresholdScore = reader.readNext();

        parseModulesToModel(course, modules, exercises, thresholdScore);
        parseStudentsToModel(course, reader);
    }

    private static void parseModulesToModel(CsvCourse course, String[] modules, String[] exercises, String[] thresholdScore) {
        CsvModule csvModule = null;
        for (var i = START_POS; i < modules.length; i++) {
            if (!Objects.equals(modules[i], "")) {
                csvModule = new CsvModule(modules[i], new ArrayList<>(), i);
                course.modules.add(csvModule);
            }
            if (csvModule != null) {
                var threshold = StringExtensions.tryParseInt(thresholdScore[i]);
                if (threshold == -1) {
                    continue;
                }
                csvModule.exercises.add(new CsvExercise(exercises[i], threshold));
            }
        }
    }

    private static void parseStudentsToModel(CsvCourse course, CSVReader reader) throws IOException {
        var students = reader.readAll();
        for(var student : students) {
            var name = student[0];
            var groupName = student[1];
            var csvStudent = new CsvStudent(name, groupName, new HashMap<>());
            for (var module : course.modules) {
                for (var i = 0; i < module.exercises.size(); i++) {
                    var value = StringExtensions.tryParseInt(student[module.startPosition + i]);
                    if (value == -1) {
                        continue;
                    }
                    var key = module.name + module.exercises.get(i).name;
                    csvStudent.exerciseToScoreDict.put(key, value);
                }
            }
            course.students.add(csvStudent);
        }
    }
}
