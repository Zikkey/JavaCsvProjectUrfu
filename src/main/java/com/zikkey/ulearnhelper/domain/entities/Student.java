package com.zikkey.ulearnhelper.domain.entities;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<StudentScore> studentScores;
    @Column(name = "group_name")
    public String group;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    public PersonInfo personInfo;
}
