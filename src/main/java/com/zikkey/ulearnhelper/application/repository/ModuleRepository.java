package com.zikkey.ulearnhelper.application.repository;

import com.zikkey.ulearnhelper.domain.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
