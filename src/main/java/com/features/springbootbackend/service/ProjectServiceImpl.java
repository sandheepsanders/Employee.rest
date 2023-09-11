package com.features.springbootbackend.service;

import com.features.springbootbackend.exception.ResourceNotFoundException;
import com.features.springbootbackend.model.Project;
import com.features.springbootbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(int id, Project updatedProject) {
        Project project = getProjectById(id);
        project.setProjectName(updatedProject.getProjectName());
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}

