package com.recon.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.InternshipDao;
import com.recon.entity.InternshipDetails;
import com.recon.service.InternshipService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service
public class InternshipServiceImpl implements InternshipService {

	@Autowired
	private InternshipDao internDao;

	@Autowired
	UserService userservice;

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Override
	public String addInternshipDetails(InternshipDetails interndetails) {
		// TODO Auto-generated method stub
		interndetails.setUserinfo(userservice.getCurrentUser());
		return internDao.addInternshipDetails(interndetails);
	}

	@Override
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails) throws NotFoundException {
		// TODO Auto-generated method stub
		interndetails.setUserinfo(userservice.getCurrentUser());
		return internDao.updateInternshipDetails(interndetails);
	}

	@Override
	public int removeInternshipDetails(Long internId) {
		return internDao.removeInternshipDetails(internId);
	}

	@Override
	public List<InternshipDetails> getAllInternshipDetails() {
		// TODO Auto-generated method stub
		return internDao.getAllInternshipDetails();
	}

	@Override
	public InternshipDetails findByInternshipId(Long internId) {
		// TODO Auto-generated method stub
		return internDao.findByInternshipId(internId);
	}

	@Override
	public List<InternshipDetails> getInternshipDetailsByUser(String username) {
		// TODO Auto-generated method stub
		logger.debug("[SERVICE] get details by user: {}", username);
		return internDao.getInternshipDetailsByUser(username);
	}

	@Override
	public InternshipDetails findbyInternshipIDandUsername(Long internshipId, String username) {
		// TODO Auto-generated method stub
		return internDao.findbyInternshipIDandUsername(internshipId, username);
	}

}
