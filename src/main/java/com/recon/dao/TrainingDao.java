package com.recon.dao;

import java.util.List;

import com.recon.entity.TrainingDetails;

import javassist.NotFoundException;

public interface TrainingDao {
	public String addTrainingDetails(TrainingDetails trainingdetails);

	public TrainingDetails updateTrainingDetails(TrainingDetails trainingdetails) throws NotFoundException;

	public int removeTrainingDetails(Long trainingId);

	public List<TrainingDetails> getAllTrainingDetails();

	public TrainingDetails findByTrainingId(Long trainingId);

	public List<TrainingDetails> getTrainingDetailsByUser(String username);

	public TrainingDetails findbyTrainingIDandUsername(Long trainingId, String username);

}
