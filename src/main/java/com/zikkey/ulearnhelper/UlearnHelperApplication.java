package com.zikkey.ulearnhelper;

import com.vk.api.sdk.objects.base.Sex;
import com.zikkey.ulearnhelper.application.repository.*;
import com.zikkey.ulearnhelper.domain.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;

@Log4j2
@SpringBootApplication
public class UlearnHelperApplication implements CommandLineRunner {
    private final CourseRepository courseRepository;
    private final ExerciseRepository exerciseRepository;
    private final StudentRepository studentRepository;

    public UlearnHelperApplication(CourseRepository courseRepository, ExerciseRepository exerciseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.exerciseRepository = exerciseRepository;
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UlearnHelperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var personInfo = new PersonInfo();
        personInfo.birthdayDate = new Date();
        personInfo.city = "Moscow";
        personInfo.SetSex(Sex.MALE);
        personInfo.timeZone = 3;
        var student = new Student();
        student.personInfo = personInfo;
        student.group = "РИ-210044дк";
        student.studentScores = new HashSet<>();
        studentRepository.save(student);
    }
}
