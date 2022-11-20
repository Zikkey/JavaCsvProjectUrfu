package com.metanit;

import com.metanit.application.interfaces.ICsvParseHelper;
import com.metanit.application.interfaces.IVkApiHelper;
import com.metanit.domain.entities.Course;
import com.metanit.domain.entities.Module;
import com.metanit.infrastucture.persistence.DbSessionFactory;
import com.metanit.infrastucture.services.CsvParseHelper;
import com.metanit.infrastucture.services.VkApiHelper;
import com.metanit.application.models.csv.CsvCourse;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException, ClientException, ApiException, ParseException {
        //var course = new CsvCourse("BasicProgramming", new ArrayList<>(), new ArrayList<>());
        //ICsvParseHelper csvHelper = new CsvParseHelper();
        //IVkApiHelper vkApiHelper = new VkApiHelper();
        //csvHelper.parseCsvToModel(course, "basicprogramming_2.csv");
        //var test = vkApiHelper.findUserInfo("Егор Земский");
        var entityManager = new DbSessionFactory().getSessionFactory().createEntityManager();
        entityManager.getTransaction().begin();
        var course = new Course();
        course.name = "TestingCourse";
        course.modules = new HashSet<>();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
