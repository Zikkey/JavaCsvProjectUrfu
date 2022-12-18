package com.zikkey.ulearnhelper.domain.entities;


import com.vk.api.sdk.objects.base.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "person_info")
public class PersonInfo extends BaseEntity {
    private String name;
    @Column(name = "birthday_date")
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;
    private String city;
    @Column(name = "sex")
    private String sexStr;
    @Column(name = "time_zone")
    private float timeZone;

    public Sex getSex() {
        return Sex.valueOf(sexStr);
    }

    public void setSex(Sex sex) {
        sexStr = sex.toString();
    }
}
