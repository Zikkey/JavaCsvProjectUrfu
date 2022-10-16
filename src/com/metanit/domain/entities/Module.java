package com.metanit.domain.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Module implements BaseEntity {
    public UUID id;
    public String name;
    public ArrayList<Exercise> exercises;
    public Course course;
}
