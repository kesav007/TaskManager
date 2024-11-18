package com.assysttech.TaskManager.repository;

import com.assysttech.TaskManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
