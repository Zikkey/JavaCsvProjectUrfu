package com.zikkey.ulearnhelper.domain.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "module")
public class Module extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exercise> exercises = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
        exercise.setModule(this);
    }

    public void removeExercise(Exercise exercise) {
        exercises.remove(exercise);
        exercise.setModule(null);
    }
}
