package com.recon.dao;

import java.util.List;

import com.recon.entity.EducationDetails;

public interface EducationDao {
	public String addEducationDetails(EducationDetails edu);
	public EducationDetails updateEducationDetails(EducationDetails edu);
	public int removeEducationDetails(Long eduId);
	public List<EducationDetails> getAllEducationDetails();
	public EducationDetails findByEducationId(Long eduId);
	public EducationDetails getEducationDetailsByUser(String username);
}
