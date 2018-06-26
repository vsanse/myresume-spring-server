package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.recon.dao.ProjectDao;
import com.recon.entity.ProjectDetails;

import javassist.NotFoundException;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@PersistenceContext
	private EntityManager emg;

	@Override
	public void addProjectDetails(ProjectDetails project) {
		emg.persist(project);

	}

	@Override
	public void removeProjectDetails(Long pid) {
		ProjectDetails project = getProjectByPid(pid);
		emg.remove(project);

	}

	@Override
	public ProjectDetails updateProject(ProjectDetails project) throws NotFoundException {
		if(getProjectByPid(project.getPid())==null)
			throw new NotFoundException("item doesn't exists");
		return emg.merge(project);
	}

	@Override
	public ProjectDetails getProjectByPid(Long pid) {
		return emg.find(ProjectDetails.class, pid);
	}

	@Override
	public List<ProjectDetails> getAllProjectsByUsername(String username) {
		Query query = emg.createQuery("from ProjectDetails where userinfo.username like :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public ProjectDetails getProjectByUsernameandPid(String username, Long pid) {
		try {
			Query query = emg.createQuery("from ProjectDetails where username like :username and pid like :pid")
					.setParameter("username", username).setParameter("pid", pid);
			return (ProjectDetails) query.getSingleResult();
		} catch (RuntimeException e) {
			logger.error("NO SUCH RESULT FOUND");
			return null;
		}
	}

	@Override
	public List<ProjectDetails> getAllProjects() {

		return emg.createQuery("from ProjectDetails").getResultList();
	}

}
