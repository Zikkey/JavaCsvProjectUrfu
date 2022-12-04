package com.zikkey.ulearnhelper.application.repository;

import com.zikkey.ulearnhelper.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
