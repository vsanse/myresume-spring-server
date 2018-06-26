package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.TrainingDao;
import com.recon.entity.TrainingDetails;

import javassist.NotFoundException;

@Repository
@Transactional
public class TrainingDaoImpl implements TrainingDao {

	@PersistenceContext
	private EntityManager emg;

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Override
	public String addTrainingDetails(TrainingDetails trainingdetails) {
		emg.persist(trainingdetails);
		return "Success";
	}

	@Override
	public TrainingDetails updateTrainingDetails(TrainingDetails trainingdetails) throws NotFoundException {
		if (findByTrainingId(trainingdetails.getTrainingId()) == null) {
			throw new NotFoundException("item doesn't exists");
		}
		return emg.merge(trainingdetails);
	}

	@Override
	public int removeTrainingDetails(Long trainingId) {
		logger.info("inside delete user");
		TrainingDetails training = findByTrainingId(trainingId);
		emg.remove(training);
		return 1;
	}

	@Override
	public List<TrainingDetails> getAllTrainingDetails() {
		Query query = emg.createQuery("from TrainingDetails");
		return query.getResultList();
	}

	@Override
	public TrainingDetails findByTrainingId(Long trainingId) {
		return emg.find(TrainingDetails.class, trainingId);
	}

	@Override
	public List<TrainingDetails> getTrainingDetailsByUser(String username) {
		Query query = emg.createQuery("from TrainingDetails where userinfo.username like :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public TrainingDetails findbyTrainingIDandUsername(Long trainingId, String username) {
		Query query = emg
				.createQuery("from TrainingDetails where userinfo.username like :username and trainingId=:trainingId");
		try {
			return (TrainingDetails) query.setParameter("username", username).setParameter("trainingId", trainingId)
					.getSingleResult();
		} catch (RuntimeException e) {
			logger.error("ERROR NO RESULT FOUND");
			return null;
		}
	}

}
