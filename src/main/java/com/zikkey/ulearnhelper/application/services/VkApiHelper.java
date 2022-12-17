package com.zikkey.ulearnhelper.application.services;

import com.zikkey.ulearnhelper.application.interfaces.IVkApiHelper;
import com.zikkey.ulearnhelper.application.models.vk.VkUserInfo;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class VkApiHelper implements IVkApiHelper {
    private final VkApiClient CLIENT = new VkApiClient(new HttpTransportClient());
    private final Integer APP_ID = 102609066;
    private final String TOKEN = "SECRET";

    private UserActor getUserActor() {
        return new UserActor(APP_ID, TOKEN);
    }

    @Override
    public VkUserInfo findUserInfo(String search) throws ClientException, ApiException, ParseException {
        var actor = getUserActor();
        var result = CLIENT.users().search(actor)
                .count(1)
                .groupId(22941070)
                .q(search)
                .fields(Fields.CITY, Fields.SEX, Fields.BDATE, Fields.TIMEZONE)
                .execute();
        var userInfo = result.getItems().get(0);
        var parser = new SimpleDateFormat("dd.MM.yyyy");
        var bDate = parser.parse(userInfo.getBdate());
        var name = userInfo.getFirstName() + " " + userInfo.getLastName();
        return new VkUserInfo(bDate, userInfo.getCity().getTitle(), userInfo.getSex(), userInfo.getTimezone(), name);
    }
}
