package com.metanit.domain.entities;

import java.util.UUID;

public class StudentScore implements BaseEntity {
    public UUID id;
    public Exercise exercise;
    public Student student;
    public int score;
}
