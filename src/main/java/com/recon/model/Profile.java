package com.recon.model;

import java.util.List;

import com.recon.entity.EducationDetails;
import com.recon.entity.InternshipDetails;
import com.recon.entity.ProjectDetails;
import com.recon.entity.TrainingDetails;
import com.recon.entity.SkillsDetails;
import com.recon.entity.UserInfo;
import com.recon.entity.AdditionalDetails;

public class Profile {

	private UserInfo userinfo;
	private List<EducationDetails> educationDetails;
	private List<InternshipDetails> internshipDetails;
	private List<TrainingDetails> trainingDetails;
	private List<ProjectDetails> projectDetails;
        private List<SkillsDetails> skillsDetails;
	private List<AdditionalDetails> additionalDetails;

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

	public List<ProjectDetails> getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(List<ProjectDetails> projectDetails) {
		this.projectDetails = projectDetails;
	}
	
	public List<SkillsDetails> getSkillsDetails() {
		return skillsDetails;
	}

	public void setSkillsDetails(List<SkillsDetails> skillsDetails) {
		this.skillsDetails = skillsDetails;
	}
	

	public List<AdditionalDetails> getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(List<AdditionalDetails> additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	
}
