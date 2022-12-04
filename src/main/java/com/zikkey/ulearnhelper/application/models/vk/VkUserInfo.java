package com.zikkey.ulearnhelper.application.models.vk;

import com.vk.api.sdk.objects.base.Sex;

import java.util.Date;

public class VkUserInfo {
    private final Date birthdayDate;
    private final String city;
    private final Sex sex;
    private final float timeZone;
    private final String name;

    public VkUserInfo(Date birthdayDate, String city, Sex sex, float timeZone, String name) {
        this.birthdayDate = birthdayDate;
        this.city = city;
        this.sex = sex;
        this.timeZone = timeZone;
        this.name = name;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public String getCity() {
        return city;
    }

    public Sex getSex() {
        return sex;
    }

    public float getTimeZone() {
        return timeZone;
    }

    public String getName() {
        return name;
    }
}
