package com.zikkey.ulearnhelper.console.commands;

import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import com.zikkey.ulearnhelper.application.repository.CourseRepository;
import com.zikkey.ulearnhelper.domain.entities.Course;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CoursesCommand implements ICommand {
    private final CourseRepository courseRepository;
    private final Level APP = Level.getLevel("APP");

    public CoursesCommand(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public String getName() {
        return "courses";
    }

    @Override
    public String getDescription() {
        return "получает список внесенных курсов";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void run(List<String> args) {
        var courses = String.join(", ", courseRepository.findAll()
                .stream()
                .map(Course::getName)
                .toList());
        print("Курсы: " + courses);
    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
