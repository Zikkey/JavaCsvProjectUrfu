package com.zikkey.ulearnhelper.domain.entities;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "student_score")
public class StudentScore extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    public Exercise exercise;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    public Student student;
    public int score;
}
