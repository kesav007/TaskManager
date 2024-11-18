package com.assysttech.TaskManager.service;

import com.assysttech.TaskManager.entity.Project;
import com.assysttech.TaskManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return this.projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return this.projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return this.projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        this.projectRepository.deleteById(id);
    }
}
