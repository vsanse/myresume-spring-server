package com.recon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.EducationDao;
import com.recon.entity.EducationDetails;
import com.recon.service.EducationService;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationDao edudao;

	@Override
	public String addEducationDetails(EducationDetails edu) {
		return edudao.addEducationDetails(edu);
	}

	@Override
	public EducationDetails updateEducationDetails(EducationDetails edu) {

		return edudao.updateEducationDetails(edu);
	}

	@Override
	public int removeEducationDetails(Long eduId) {

		return edudao.removeEducationDetails(eduId);
	}

	@Override
	public List<EducationDetails> getAllEducationDetails(String username) {

		return edudao.getAllEducationDetails();
	}

	@Override
	public EducationDetails findByEducationId(Long eduId) {
		return edudao.findByEducationId(eduId);
	}

}
