package com.features.springbootbackend.service;

import com.features.springbootbackend.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(int id);
    Project addProject(Project project);
    Project updateProject(int id, Project updatedProject);
    void deleteProject(int id);
}
