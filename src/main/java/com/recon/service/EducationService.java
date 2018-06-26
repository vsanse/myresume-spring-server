package com.recon.service;

import java.util.List;

import com.recon.entity.EducationDetails;

import javassist.NotFoundException;

public interface EducationService {
	public String addEducationDetails(EducationDetails edu);
	public EducationDetails updateEducationDetails(EducationDetails edu) throws NotFoundException;
	public int removeEducationDetails(Long eduId);
	public List<EducationDetails> getEducationDetailsByUser(String username);
	public EducationDetails findByEducationId(Long eduId);
	public List<EducationDetails> getAllEducationDetials();
	public EducationDetails findbyEduIDandUsername(Long eduId, String username);
}
