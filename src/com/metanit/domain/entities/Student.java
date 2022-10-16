package com.metanit.domain.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Student implements BaseEntity {
    public UUID id;
    public ArrayList<StudentScore> studentScores;
    public String group;
    public PersonInfo personInfos;
}
