package com.zikkey.ulearnhelper.domain.entities;


import com.vk.api.sdk.objects.base.Sex;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "person_info")
public class PersonInfo extends BaseEntity {
    public String name;
    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    public Date birthdayDate;
    public String city;
    @Column(name = "sex")
    private String sexStr;
    @Column(name = "time_zone")
    public float timeZone;

    public Sex GetSex() {
        return Sex.valueOf(sexStr);
    }

    public void SetSex(Sex sex) {
        sexStr = sex.toString();
    }
}
