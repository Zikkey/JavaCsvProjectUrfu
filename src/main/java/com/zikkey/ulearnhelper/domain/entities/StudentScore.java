package com.zikkey.ulearnhelper.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "student_score")
public class StudentScore extends BaseEntity {
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private Exercise exercise;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
    private int score;
}
