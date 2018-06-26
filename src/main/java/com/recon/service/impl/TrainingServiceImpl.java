package com.recon.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.TrainingDao;
import com.recon.entity.TrainingDetails;
import com.recon.service.TrainingService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	UserService userservice;

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Override
	public String addTrainingDetails(TrainingDetails trainingdetails) {
		// TODO Auto-generated method stub
		trainingdetails.setUserinfo(userservice.getCurrentUser());
		return trainingDao.addTrainingDetails(trainingdetails);
	}

	@Override
	public TrainingDetails updateTrainingDetails(TrainingDetails trainingdetails) throws NotFoundException {
		// TODO Auto-generated method stub
		trainingdetails.setUserinfo(userservice.getCurrentUser());
		return trainingDao.updateTrainingDetails(trainingdetails);
	}

	@Override
	public int removeTrainingDetails(Long internId) {
		return trainingDao.removeTrainingDetails(internId);
	}

	@Override
	public List<TrainingDetails> getAllTrainingDetails() {
		// TODO Auto-generated method stub
		return trainingDao.getAllTrainingDetails();
	}

	@Override
	public TrainingDetails findByTrainingId(Long internId) {
		// TODO Auto-generated method stub
		return trainingDao.findByTrainingId(internId);
	}

	@Override
	public List<TrainingDetails> getTrainingDetailsByUser(String username) {
		// TODO Auto-generated method stub
		logger.debug("[SERVICE] get details by user: {}", username);
		return trainingDao.getTrainingDetailsByUser(username);
	}

	@Override
	public TrainingDetails findbyTrainingIDandUsername(Long trainingId, String username) {
		return trainingDao.findbyTrainingIDandUsername(trainingId, username);
	}

}
