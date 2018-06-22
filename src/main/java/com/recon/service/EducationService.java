package com.recon.service;

import java.util.List;

import com.recon.entity.EducationDetails;

public interface EducationService {
	public String addEducationDetails(EducationDetails edu);
	public EducationDetails updateEducationDetails(EducationDetails edu);
	public int removeEducationDetails(Long eduId);
	public List<EducationDetails> getAllEducationDetails(String username);
	public EducationDetails findByEducationId(Long eduId);
	public List<EducationDetails> getAllEducationDetials();

}
