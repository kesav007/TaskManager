package com.assysttech.TaskManager.controller;

import com.assysttech.TaskManager.entity.Project;
import com.assysttech.TaskManager.entity.Task;
import com.assysttech.TaskManager.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getAllProjectById(@PathVariable  Long id){
        Optional<Project> project = projectService.getProjectById(id);
        if(project.isPresent()){
            return ResponseEntity.ok(project.get());
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return this.projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project proj) {
        Optional<Project> project = this.projectService.getProjectById(id);
        if(project.isPresent()) {
            Project updatedProject = project.get();
            updatedProject.setName(proj.getName());
            updatedProject.setDescription(proj.getDescription());
            updatedProject.getTasks().clear();

            for (Task task: proj.getTasks()){
                updatedProject.getTasks().add(task);
            }
            return ResponseEntity.ok(projectService.saveProject(updatedProject));
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
