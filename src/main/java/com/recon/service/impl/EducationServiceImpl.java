package com.recon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.EducationDao;
import com.recon.entity.EducationDetails;
import com.recon.service.EducationService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationDao edudao;
	
	@Autowired UserService userservice;

	@Override
	public String addEducationDetails(EducationDetails edu) {
		
		edu.setUserinfo(userservice.getCurrentUser());
		return edudao.addEducationDetails(edu);
	}

	@Override
	public EducationDetails updateEducationDetails(EducationDetails edu) throws NotFoundException {

		edu.setUserinfo(userservice.getCurrentUser());
		return edudao.updateEducationDetails(edu);
	}

	@Override
	public int removeEducationDetails(Long eduId) {
		
		return edudao.removeEducationDetails(eduId);
	}

	@Override
	public List<EducationDetails> getEducationDetailsByUser(String username) {

		return edudao.getEducationDetailsByUser(username);
	}

	@Override
	public EducationDetails findByEducationId(Long eduId) {
		return edudao.findByEducationId(eduId);
	}

	@Override
	public List<EducationDetails> getAllEducationDetials() {
		return edudao.getAllEducationDetails();
	}

	@Override
	public EducationDetails findbyEduIDandUsername(Long eduId, String username) {
		return edudao.findbyEduIDandUsername(eduId, username);
	}
	
	

}
