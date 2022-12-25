package com.zikkey.ulearnhelper.application.services;

import com.zikkey.ulearnhelper.application.aggregations.CourseStudentsAggregation;
import com.zikkey.ulearnhelper.application.models.csv.CsvCourse;
import com.zikkey.ulearnhelper.application.models.csv.CsvExercise;
import com.zikkey.ulearnhelper.application.models.csv.CsvModule;
import com.zikkey.ulearnhelper.application.models.csv.CsvStudent;
import com.zikkey.ulearnhelper.application.utils.ComposeKey;
import com.zikkey.ulearnhelper.domain.entities.*;
import com.zikkey.ulearnhelper.domain.entities.Module;
import com.zikkey.ulearnhelper.domain.enums.ExerciseType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseCsvToDomainMapper {
    public CourseStudentsAggregation toDomain(CsvCourse csvCourse) {
        var course = new Course();
        course.setName(csvCourse.name);
        var modules = mapModules(csvCourse.modules);
        modules.forEach(course::addModule);
        var students = mapStudents(course, csvCourse.students);
        return new CourseStudentsAggregation(course, new ArrayList<>(students));
    }

    private Set<Module> mapModules(ArrayList<CsvModule> csvModules) {
        return csvModules.stream().map(csvMod -> {
            var module = new Module();
            module.setName(csvMod.name);
            var exercises = mapExercises(csvMod.exercises);
            exercises.forEach(module::addExercise);
            return module;
        }).collect(Collectors.toSet());
    }

    private Set<Exercise> mapExercises(ArrayList<CsvExercise> csvExercises) {
        return csvExercises.stream().map(csvExer -> {
            var exercise = new Exercise();
            exercise.setThresholdScore(csvExer.thresholdScore);
            exercise.setName(csvExer.name);
            exercise.SetType(mapExerciseType(csvExer.name));
            return exercise;
        }).collect(Collectors.toSet());
    }

    private Set<Student> mapStudents(Course course, ArrayList<CsvStudent> csvStudents) {
        return csvStudents.stream().map(stud -> {
            var student = new Student();
            student.setGroup(stud.groupName);
            var personInfo = new PersonInfo();
            personInfo.setName(stud.name);
            student.setPersonInfo(personInfo);
            var studentScores = mapStudentScores(course, stud.exerciseToScoreDict);
            studentScores.forEach(student::addStudentScore);
            return student;
        }).collect(Collectors.toSet());
    }

    private HashSet<StudentScore> mapStudentScores(Course course,
            HashMap<ComposeKey<String, String>, Integer> dict) {
        var result = new HashSet<StudentScore>();
        dict.forEach((key, value) -> {
            var ex = course.getModules().stream().filter(module ->
                    module.getName().equals(key.value1)).findFirst().get().getExercises().stream().filter(exercise ->
                            exercise.getName().equals(key.value2)).findFirst().get();
            var score = new StudentScore();
            score.setExercise(ex);
            score.setScore(value);
            result.add(score);
        });
        return result;
    }

    private ExerciseType mapExerciseType(String str) {
        if (str.startsWith("Упр:"))
            return ExerciseType.Task;
        if (str.startsWith("Акт"))
            return ExerciseType.Activity;
        if (str.startsWith("Сем"))
            return ExerciseType.Seminar;
        if (str.startsWith("ДЗ:"))
            return ExerciseType.Homework;
        if (str.startsWith("Доп"))
            return ExerciseType.Additional;
        throw new IllegalArgumentException(ExerciseType.class.getName());
    }
}
