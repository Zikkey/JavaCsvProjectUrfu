package com.metanit.domain.entities;

import java.util.UUID;

import com.metanit.domain.enums.ExerciseType;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;
    public String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    public Module module;
    @Column(name = "exercise_type")
    private String exerciseTypeStr;

    public ExerciseType GetType() {
        return ExerciseType.valueOf(exerciseTypeStr);
    }

    public void SetType(ExerciseType type) {
        exerciseTypeStr = type.toString();
    }
}
