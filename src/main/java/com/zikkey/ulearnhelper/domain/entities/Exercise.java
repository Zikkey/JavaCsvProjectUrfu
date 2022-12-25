package com.zikkey.ulearnhelper.domain.entities;

import com.zikkey.ulearnhelper.domain.enums.ExerciseType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "exercise")
public class Exercise extends BaseEntity {
    private String name;
    @Column(name = "threshold_score")
    private int thresholdScore;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id")
    private Module module;
    @Column(name = "exercise_type")
    private String exerciseTypeStr;

    public ExerciseType GetType() {
        return ExerciseType.valueOf(exerciseTypeStr);
    }

    public void SetType(ExerciseType type) {
        exerciseTypeStr = type.toString();
    }
}
