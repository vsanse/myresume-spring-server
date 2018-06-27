package com.recon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.SkillsDao;
import com.recon.entity.SkillsDetails;
import com.recon.service.SkillsService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service

public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsDao skillsDao;

	@Autowired
	private UserService userService;

	@Override
	public void addSkills(SkillsDetails skill) {
		skill.setUserinfo(userService.getCurrentUser());
		skillsDao.addSkills(skill);
	}

	@Override
	public void removeSkill(Long skillId) {
		skillsDao.removeSkill(skillId);

	}

	@Override
	public SkillsDetails updateSkill(SkillsDetails skill) throws NotFoundException {
		skill.setUserinfo(userService.getCurrentUser());
		return skillsDao.updateSkill(skill);
	}

	@Override
	public List<SkillsDetails> getSkillsByUsername(String username) {

		return skillsDao.getSkillsByUsername(username);
	}

	@Override
	public SkillsDetails getSkillById(Long Id) {

		return skillsDao.getSkillById(Id);
	}

	@Override
	public SkillsDetails getSkillByIdUsername(Long skillId, String username) {

		return skillsDao.getSkillByIdUsername(skillId, username);
	}

	@Override
	public List<SkillsDetails> getAllSkills() {

		return skillsDao.getAllSkills();
	}

}
