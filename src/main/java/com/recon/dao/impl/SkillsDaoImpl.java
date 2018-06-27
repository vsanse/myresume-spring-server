package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.SkillsDao;
import com.recon.entity.SkillsDetails;

import javassist.NotFoundException;

@Repository
@Transactional
public class SkillsDaoImpl implements SkillsDao {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private EntityManager emg;

	@Override
	public void addSkills(SkillsDetails skill) {
		emg.persist(skill);
	}

	@Override
	public void removeSkill(Long skillId) {
		SkillsDetails skill = getSkillById(skillId);
		if (skill != null) {
			emg.remove(skill);
		}
	}

	@Override
	public SkillsDetails updateSkill(SkillsDetails skill) throws NotFoundException {
		if (getSkillById(skill.getSkillId()) == null) {
			throw new NotFoundException("item doesn't exists");
		}
		return emg.merge(skill);
	}

	@Override
	public List<SkillsDetails> getSkillsByUsername(String username) {
		Query query = emg.createQuery("from SkillsDetails where userinfo.username like :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public SkillsDetails getSkillById(Long Id) {
		return emg.find(SkillsDetails.class, Id);
	}

	@Override
	public SkillsDetails getSkillByIdUsername(Long skillId, String username) {
		try {
			Query query = emg.createQuery("from SkillsDetails where username like :username and skillId like :skillId")
					.setParameter("username", username).setParameter("skillId", skillId);
			return (SkillsDetails) query.getSingleResult();
		} catch (RuntimeException e) {
			logger.error("NO SUCH RESULT FOUND");
			return null;
		}
	}

	@Override
	public List<SkillsDetails> getAllSkills() {
		return emg.createQuery("from SkillsDetails").getResultList();
	}

}
