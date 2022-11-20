package com.metanit.domain.entities;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "student_score")
public class StudentScore implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    public Exercise exercise;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    public Student student;
    public int score;
}
