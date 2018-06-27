package com.recon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.ProjectDao;
import com.recon.entity.ProjectDetails;
import com.recon.service.ProjectService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service	
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao; 
	
	@Autowired
	private UserService userService;
	
	@Override
	public void addProjectDetails(ProjectDetails project) {
		project.setUserinfo(userService.getCurrentUser());
		projectDao.addProjectDetails(project);

	}

	@Override
	public void removeProjectDetails(Long pid) {
		projectDao.removeProjectDetails(pid);

	}

	@Override
	public ProjectDetails updateProject(ProjectDetails project) throws NotFoundException{
		project.setUserinfo(userService.getCurrentUser());
		return projectDao.updateProject(project);
	}

	@Override
	public ProjectDetails getProjectByPid(Long pid) {
		return projectDao.getProjectByPid(pid);
	}

	@Override
	public List<ProjectDetails> getAllProjectsByUsername(String username) {
		
		return projectDao.getAllProjectsByUsername(username);
	}

	@Override
	public ProjectDetails getProjectByUsernameandPid(String username, Long pid) {

		return projectDao.getProjectByUsernameandPid(username, pid);
	}

	@Override
	public List<ProjectDetails> getAllProjects() {
		
		return projectDao.getAllProjects();
	}

}
