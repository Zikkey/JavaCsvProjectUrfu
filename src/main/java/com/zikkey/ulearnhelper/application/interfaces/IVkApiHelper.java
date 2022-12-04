package com.zikkey.ulearnhelper.application.interfaces;

import com.zikkey.ulearnhelper.application.models.vk.VkUserInfo;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.text.ParseException;

public interface IVkApiHelper {
    VkUserInfo findUserInfo(String search) throws ClientException, ApiException, ParseException;
}
