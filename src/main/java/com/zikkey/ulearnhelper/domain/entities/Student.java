package com.zikkey.ulearnhelper.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentScore> studentScores = new HashSet<>();
    @Column(name = "group_name")
    private String group;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    private PersonInfo personInfo;

    public void addStudentScore(StudentScore studentScore) {
        studentScores.add(studentScore);
        studentScore.setStudent(this);
    }

    public void removeStudentScore(StudentScore studentScore) {
        studentScores.remove(studentScore);
        studentScore.setStudent(null);
    }
}
