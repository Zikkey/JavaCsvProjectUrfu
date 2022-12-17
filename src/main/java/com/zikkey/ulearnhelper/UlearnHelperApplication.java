package com.zikkey.ulearnhelper;

import com.zikkey.ulearnhelper.application.interfaces.ICsvParseHelper;
import com.zikkey.ulearnhelper.application.repository.*;
import com.zikkey.ulearnhelper.application.utils.CommandListener;
import com.zikkey.ulearnhelper.application.utils.CommandRegistry;
import com.zikkey.ulearnhelper.domain.entities.Course;
import com.zikkey.ulearnhelper.domain.entities.Module;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;

@Log4j2
@SpringBootApplication
public class UlearnHelperApplication implements CommandLineRunner {
    private final CourseRepository courseRepository;
    private final ExerciseRepository exerciseRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;
    private final CommandListener listener;
    private final CommandRegistry registry;
    private final ICsvParseHelper parser;
    private final Level APP = Level.getLevel("APP");

    public UlearnHelperApplication(CourseRepository courseRepository, ExerciseRepository exerciseRepository, StudentRepository studentRepository, ModuleRepository moduleRepository, CommandListener listener, CommandRegistry registry, ICsvParseHelper parser) {
        this.courseRepository = courseRepository;
        this.exerciseRepository = exerciseRepository;
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
        this.listener = listener;
        this.registry = registry;
        this.parser = parser;
    }

    public static void main(String[] args) {
        SpringApplication.run(UlearnHelperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*var personInfo = new PersonInfo();
        personInfo.birthdayDate = new Date();
        personInfo.city = "Moscow";
        personInfo.SetSex(Sex.MALE);
        personInfo.timeZone = 3;
        var student = new Student();
        student.personInfo = personInfo;
        student.group = "РИ-210044дк";
        student.studentScores = new HashSet<>();
        studentRepository.save(student);
        var course = new Course();
        course.name = "bsbsbsb";
        course.modules = new HashSet<>();
        var module = new Module();
        module.name = "booba";
        course.modules.add(module);
        course = courseRepository.save(course);
        for (Module module1 : course.modules) {
            module1.course = course;
        }
        moduleRepository.saveAll(course.modules);*/

        registry.handleAndRegisterAll();
        print("Введите 'help', чтобы узнать список команд");
        listener.listen();
    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
