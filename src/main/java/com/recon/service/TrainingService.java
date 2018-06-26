package com.recon.service;

import java.util.List;

import com.recon.entity.InternshipDetails;
import com.recon.entity.TrainingDetails;

import javassist.NotFoundException;

public interface TrainingService {
	
	public String addTrainingDetails(TrainingDetails trainingdetails);
	public TrainingDetails updateTrainingDetails(TrainingDetails trainingdetails) throws NotFoundException;
	public int removeTrainingDetails(Long trainingId);
	public List<TrainingDetails> getAllTrainingDetails();
	public TrainingDetails findByTrainingId(Long trainingId);
	public List<TrainingDetails> getTrainingDetailsByUser(String username);
	public TrainingDetails findbyTrainingIDandUsername(Long trainingId, String username);

}
