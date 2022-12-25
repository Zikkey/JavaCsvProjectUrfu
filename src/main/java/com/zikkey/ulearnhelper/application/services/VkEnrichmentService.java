package com.zikkey.ulearnhelper.application.services;

import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.zikkey.ulearnhelper.application.interfaces.IVkEnrichmentService;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.zikkey.ulearnhelper.domain.entities.PersonInfo;
import com.zikkey.ulearnhelper.domain.entities.Student;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VkEnrichmentService implements IVkEnrichmentService {
    private final VkApiClient CLIENT = new VkApiClient(new HttpTransportClient());
    private final Integer APP_ID = 102609066;
    private final String TOKEN = "";

    private UserActor getUserActor() {
        return new UserActor(APP_ID, TOKEN);
    }

    @Override
    public void enrich(List<Student> students) throws ClientException, ApiException, InterruptedException {
        var actor = getUserActor();
        var persons = new HashMap<String, PersonInfo>();
        var offset = 0;
        var result = CLIENT.groups().getMembers(actor).groupId("22941070").execute();
        while (result.getCount() > offset) {
            var ids = result.getItems().stream().map(Object::toString).collect(Collectors.toList());
            var infos = CLIENT.users().get(actor)
                    .userIds(ids)
                    .fields(Fields.CITY, Fields.SEX, Fields.BDATE, Fields.TIMEZONE)
                    .execute();
            infos.stream().map(VkEnrichmentService::mapPersonInfo).forEach(i -> persons.put(i.getName(), i));
            offset += 1000;
            Thread.sleep(1000);
            result = CLIENT.groups().getMembers(actor).groupId("22941070").offset(offset).execute();
        }

        students.forEach(student -> {
            var key = student.getPersonInfo().getName();
            if (persons.containsKey(key)) {
                student.setPersonInfo(persons.get(key));
            }
        });
    }

    private static PersonInfo mapPersonInfo(GetResponse i) {
        var personInfo = new PersonInfo();
        if (i.getLastName() != null && i.getFirstName() != null)
            personInfo.setName(i.getLastName() + " " + i.getFirstName());
        if (i.getSex() != null)
            personInfo.setSex(i.getSex());
        if (i.getCity() != null)
            personInfo.setCity(i.getCity().getTitle());
        var parser = new SimpleDateFormat("dd.MM.yyyy");
        Date bDate;
        try {
            bDate = parser.parse(i.getBdate());
        } catch (Exception e) {
            bDate = Date.from(Instant.ofEpochSecond(0));
        }
        personInfo.setBirthdayDate(bDate);
        if (i.getTimezone() != null)
            personInfo.setTimeZone(i.getTimezone());
        return personInfo;
    }
}
