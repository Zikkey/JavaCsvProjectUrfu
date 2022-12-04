package com.zikkey.ulearnhelper.application.repository;

import com.zikkey.ulearnhelper.domain.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
