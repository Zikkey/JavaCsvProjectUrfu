package com.zikkey.ulearnhelper.domain.entities;

import com.zikkey.ulearnhelper.domain.enums.ExerciseType;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "exercise")
public class Exercise extends BaseEntity {
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
