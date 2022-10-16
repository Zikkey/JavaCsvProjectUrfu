package com.metanit.domain.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Course implements BaseEntity {
    public UUID id;
    public String name;
    ArrayList<Module> modules;
}
