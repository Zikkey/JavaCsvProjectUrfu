package com.metanit.domain.entities;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<StudentScore> studentScores;
    public String group;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    public PersonInfo personInfo;
}
