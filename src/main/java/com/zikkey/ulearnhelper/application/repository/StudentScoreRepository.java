package com.zikkey.ulearnhelper.application.repository;

import com.zikkey.ulearnhelper.domain.entities.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentScoreRepository extends JpaRepository<StudentScore, UUID> {

}
