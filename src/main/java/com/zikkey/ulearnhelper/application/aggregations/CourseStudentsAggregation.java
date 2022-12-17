package com.zikkey.ulearnhelper.application.aggregations;

import com.zikkey.ulearnhelper.domain.entities.Course;
import com.zikkey.ulearnhelper.domain.entities.Student;

import java.util.ArrayList;

public class CourseStudentsAggregation {
    public Course course;
    public ArrayList<Student> students;

    public CourseStudentsAggregation(Course course, ArrayList<Student> students) {
        this.course = course;
        this.students = students;
    }
}
