package com.zikkey.ulearnhelper.console.commands;

import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import com.zikkey.ulearnhelper.application.repository.CourseRepository;
import com.zikkey.ulearnhelper.application.repository.ExerciseRepository;
import com.zikkey.ulearnhelper.application.repository.StudentRepository;
import com.zikkey.ulearnhelper.application.repository.StudentScoreRepository;
import com.zikkey.ulearnhelper.application.services.ChartService;
import com.zikkey.ulearnhelper.domain.entities.Exercise;
import com.zikkey.ulearnhelper.domain.entities.Student;
import com.zikkey.ulearnhelper.domain.entities.StudentScore;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
public class ChartCommand implements ICommand {
    private final Level APP = Level.getLevel("APP");
    private final ChartService chartService;
    private final CourseRepository courseRepository;
    private final ExerciseRepository exerciseRepository;
    private final StudentRepository studentRepository;
    private final StudentScoreRepository studentScoreRepository;

    public ChartCommand(ChartService chartService, CourseRepository courseRepository, ExerciseRepository exerciseRepository, StudentRepository studentRepository, StudentScoreRepository studentScoreRepository) {
        this.chartService = chartService;
        this.courseRepository = courseRepository;
        this.exerciseRepository = exerciseRepository;
        this.studentRepository = studentRepository;
        this.studentScoreRepository = studentScoreRepository;
    }

    @Override
    public String getName() {
        return "chart";
    }

    @Override
    public String getDescription() {
        return "сохраняет диаграмму по названию курса";
    }

    @Override
    public String getUsage() {
        return "course path";
    }

    @Override
    public void run(List<String> args) {
        if (args.size() != 2) {
            log.warn("Нужно два аргумента - название курса, полный путь для сохранения файла" +
                    " (Пример: chart CourseName D:\\Downloads\\chart.png)");
            return;
        }
        var course = courseRepository.findByName(args.get(0));
        if (course == null) {
            log.warn("Курс с названием " + args.get(0) + " не найден!");
            return;
        }
        var students = studentRepository.findAll();
        //var cityFromCourse = scoresS.stream().map(x -> x.getPersonInfo().getCity()).toList();
        //var sexFromCourse = scoresS.stream().map(x -> x.getPersonInfo().getSexStr()).toList();
        try {
            chartService.createPerformanceChart(args.get(1), course, students);
        } catch(Exception e) {
            log.error(e.getMessage());
        }

    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
