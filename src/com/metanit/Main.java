package com.metanit;

import com.metanit.domain.helpers.CsvParseHelper;
import com.metanit.domain.helpers.VkApiHelper;
import com.metanit.domain.models.CsvCourse;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClientException, ApiException, ParseException {
        var course = new CsvCourse("BasicProgramming", new ArrayList<>(), new ArrayList<>());
        CsvParseHelper.parseCsvToModel(course, "basicprogramming_2.csv");
        var test = VkApiHelper.findUserInfo("Егор Земский");
    }
}
