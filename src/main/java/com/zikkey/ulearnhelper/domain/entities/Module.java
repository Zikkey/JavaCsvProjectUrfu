package com.zikkey.ulearnhelper.domain.entities;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "module")
public class Module extends BaseEntity {
    public String name;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Exercise> exercises;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Course course;
}
