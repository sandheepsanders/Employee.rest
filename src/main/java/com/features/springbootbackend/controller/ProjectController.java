package com.features.springbootbackend.controller;

import com.features.springbootbackend.model.Project;
import com.features.springbootbackend.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectServiceImpl.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {

        Project addedProject = projectServiceImpl.addProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project) {
        Project updatedProject = projectServiceImpl.updateProject(id, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        projectServiceImpl.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
