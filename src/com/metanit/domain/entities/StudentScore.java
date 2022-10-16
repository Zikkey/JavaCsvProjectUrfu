package com.metanit.domain.entities;

import java.util.UUID;

public class StudentScore implements BaseEntity {
    public UUID id;
    public Exercise exercises;
    public Student student;
    public int score;
}
