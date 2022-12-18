package com.zikkey.ulearnhelper.application.interfaces;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.zikkey.ulearnhelper.domain.entities.Student;

import java.text.ParseException;
import java.util.List;

public interface IVkEnrichmentService {

    void enrich(List<Student> students) throws ClientException, ApiException, ParseException, InterruptedException;
}
