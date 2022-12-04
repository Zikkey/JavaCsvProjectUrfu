package com.zikkey.ulearnhelper.application.repository;

import com.zikkey.ulearnhelper.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

}
