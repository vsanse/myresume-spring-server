package com.recon.service;

import java.util.List;

import com.recon.entity.ProjectDetails;

import javassist.NotFoundException;

public interface ProjectService {
	public void addProjectDetails(ProjectDetails project);

	public void removeProjectDetails(Long pid);

	public ProjectDetails updateProject(ProjectDetails project) throws NotFoundException;

	public ProjectDetails getProjectByPid(Long pid);

	public List<ProjectDetails> getAllProjectsByUsername(String username);

	public ProjectDetails getProjectByUsernameandPid(String username, Long pid);

	public List<ProjectDetails> getAllProjects();

}
