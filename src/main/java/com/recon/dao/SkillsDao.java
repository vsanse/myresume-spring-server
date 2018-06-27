package com.recon.dao;

import java.util.List;

import com.recon.entity.SkillsDetails;

import javassist.NotFoundException;

public interface SkillsDao {

	public void addSkills(SkillsDetails skill);

	public void removeSkill(Long skillId);

	public SkillsDetails updateSkill(SkillsDetails skill) throws NotFoundException;

	public List<SkillsDetails> getSkillsByUsername(String username);

	public SkillsDetails getSkillById(Long Id);

	public SkillsDetails getSkillByIdUsername(Long skillId, String username);

	public List<SkillsDetails> getAllSkills();

}
