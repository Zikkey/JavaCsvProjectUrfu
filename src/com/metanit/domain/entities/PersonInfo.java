package com.metanit.domain.entities;

import java.util.Date;
import java.util.UUID;

import com.metanit.domain.enums.ExerciseType;
import com.vk.api.sdk.objects.base.Sex;
import jakarta.persistence.*;

@Entity
@Table(name = "person_info")
public class PersonInfo implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
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
