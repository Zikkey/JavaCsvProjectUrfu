package com.metanit.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    public String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Module> modules;
}
