package com.zikkey.ulearnhelper.console.commands;

import com.zikkey.ulearnhelper.application.interfaces.ICsvParseHelper;
import com.zikkey.ulearnhelper.application.interfaces.command.ICommand;
import com.zikkey.ulearnhelper.application.models.csv.CsvCourse;
import com.zikkey.ulearnhelper.application.repository.*;
import com.zikkey.ulearnhelper.application.services.CourseCsvToDomainMapper;
import com.zikkey.ulearnhelper.application.services.VkEnrichmentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Log4j2
public class ParseCommand implements ICommand {
    private final Level APP = Level.getLevel("APP");
    private final VkEnrichmentService enrich;
    private final ICsvParseHelper parser;
    private final CourseCsvToDomainMapper mapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public ParseCommand(VkEnrichmentService enrich, ICsvParseHelper parser, CourseCsvToDomainMapper mapper,
                        CourseRepository courseRepository, StudentRepository studentRepository) {
        this.enrich = enrich;
        this.parser = parser;
        this.mapper = mapper;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public String getName() {
        return "parse";
    }

    @Override
    public String getDescription() {
        return "парсит CSV по пути filePath и сохраняет курс с названием name";
    }

    @Override
    public String getUsage() {
        return "name filePath";
    }

    @Override
    public void run(List<String> args) {
        if (args.size() != 2) {
            log.error("Нужно два аргумента - название курса, название CSV(file.csv)");
            return;
        }
        if (!args.get(1).endsWith(".csv")) {
            log.error("Файл должен быть в формате .csv");
            return;
        }

        print("[1/3] Происходит считывание CSV...");
        var course = new CsvCourse(args.get(0).replace("\"", ""));
        try {
            parser.parseCsvToModel(course, args.get(1));
        } catch(IOException e) {
            log.error("Не удалось считать файл");
            return;
        }

        print("[2/3] Парсинг CSV закончен, получение дополнительных данных из VK...");
        var domain = mapper.toDomain(course);
        try {
            enrich.enrich(domain.students);
        } catch(Exception e) {
            log.error("Ошибка получения данных из VK.");
            return;
        }
        print("[3/3] Данные получены, происходит сохранение в базу данных...");
        courseRepository.save(domain.course);
        studentRepository.saveAll(domain.students);
        print("Курс из CSV " + args.get(1) + " успешно сохранен в базу данных под названием " + args.get(0));
        print("Количество модулей: " + domain.course.getModules().size());
        print("Количество студентов: " + domain.students.size());

    }

    private void print(String msg) {
        log.log(APP, msg);
    }
}
