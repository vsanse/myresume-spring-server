package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.EducationDao;
import com.recon.entity.EducationDetails;

@Repository
@Transactional
public class EducationDaoImpl implements EducationDao {

	@Autowired
	private EntityManager emg;
	
	private Logger logger = LoggerFactory.getLogger("myresume");
	
	@Override
	public String addEducationDetails(EducationDetails edu) {
			emg.persist(edu);
			return "Success";

	}

	@Override
	public EducationDetails updateEducationDetails(EducationDetails edu) {
			return emg.merge(edu);

	}

	@Override
	public int removeEducationDetails(Long eduId) {
		logger.info("inside delete user");
			EducationDetails edu = findByEducationId(eduId);
			emg.remove(edu);
			return 1;

	}

	@Override
	public List<EducationDetails> getAllEducationDetails() {
		Query query  = emg.createQuery("from EducationDetails");
		return query.getResultList();
	}

	@Override
	public EducationDetails findByEducationId(Long eduId) {
		if(eduId !=null){
			return emg.find(EducationDetails.class, eduId);
		}
		return null;
	}

	@Override
	public List<EducationDetails> getEducationDetailsByUser(String username) {
		logger.info("USERNAME: "+username);
		Query query  = emg.createQuery("from EducationDetails where userinfo.username like :username");
		return  query.setParameter("username", username).getResultList();

	}

	@Override
	public EducationDetails findbyEduIDandUsername(Long eduId, String username) {
		Query query  = emg.createQuery("from EducationDetails where userinfo.username like :username and educationId=:eduID");
		try {
			return  (EducationDetails) query.setParameter("username", username)
					.setParameter("eduID", eduId)
					.getSingleResult();
		}
		catch(RuntimeException e) {
			logger.error("ERROR NO RESULT FOUND");
			return null;
		}

	}

}
