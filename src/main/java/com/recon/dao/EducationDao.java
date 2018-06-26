package com.recon.dao;

import java.util.List;

import com.recon.entity.EducationDetails;

import javassist.NotFoundException;

public interface EducationDao {
	public String addEducationDetails(EducationDetails edu);
	public EducationDetails updateEducationDetails(EducationDetails edu) throws NotFoundException;
	public int removeEducationDetails(Long eduId);
	public List<EducationDetails> getAllEducationDetails();
	public EducationDetails findByEducationId(Long eduId);
	public List<EducationDetails> getEducationDetailsByUser(String username);
	public EducationDetails findbyEduIDandUsername(Long eduId, String username);
}
