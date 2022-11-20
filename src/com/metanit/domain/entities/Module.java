package com.metanit.domain.entities;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "module")
public class Module implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    public String name;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Exercise> exercises;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    public Course course;
}
