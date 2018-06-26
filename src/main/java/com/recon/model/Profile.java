package com.recon.model;

import java.util.List;

import com.recon.entity.EducationDetails;
import com.recon.entity.InternshipDetails;
import com.recon.entity.TrainingDetails;
import com.recon.entity.UserInfo;

public class Profile {

	private UserInfo userinfo;
	private List<EducationDetails> educationDetails;
	private List<InternshipDetails> internshipDetails;
	private List<TrainingDetails>	trainingDetails;
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}
	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}
	public List<InternshipDetails> getInternshipDetails() {
		return internshipDetails;
	}
	public void setInternshipDetails(List<InternshipDetails> internshipDetails) {
		this.internshipDetails = internshipDetails;
	}
	public List<TrainingDetails> getTrainingDetails() {
		return trainingDetails;
	}
	public void setTrainingDetails(List<TrainingDetails> trainingDetails) {
		this.trainingDetails = trainingDetails;
	}
	
	
	
	
	
}
