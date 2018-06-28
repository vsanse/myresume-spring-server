package com.recon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.AdditionalDetailsDao;
import com.recon.entity.AdditionalDetails;
import com.recon.service.AdditionalDetailsService;
import com.recon.service.UserService;

import javassist.NotFoundException;

@Service

public class AdditionalDetailsServiceImpl implements AdditionalDetailsService {

	@Autowired
	private AdditionalDetailsDao  additionalDetailsDao;

	@Autowired
	private UserService userService;

	@Override
	public void addAdditionalDetails(AdditionalDetails additionalDetail) {
		additionalDetail.setUserinfo(userService.getCurrentUser());
		additionalDetailsDao.addAdditionalDetails(additionalDetail);
	}

	@Override
	public void removeAdditionalDetails(Long additionalDetailId) {
		additionalDetailsDao.removeAdditionalDetails(additionalDetailId);

	}

	@Override
	public AdditionalDetails updateAdditionalDetails(AdditionalDetails additionalDetail) throws NotFoundException {
		additionalDetail.setUserinfo(userService.getCurrentUser());
		return additionalDetailsDao.updateAdditionalDetails(additionalDetail);
	}

	@Override
	public List<AdditionalDetails> getAdditionalDetailsByUsername(String username) {

		return additionalDetailsDao.getAdditionalDetailsByUsername(username);
	}

	@Override
	public AdditionalDetails getAdditionalDetailsById(Long Id) {

		return additionalDetailsDao.getAdditionalDetailsById(Id);
	}

	@Override
	public AdditionalDetails getAdditionalDetailsByIdUsername(Long additionalDetailId, String username) {

		return additionalDetailsDao.getAdditionalDetailsByIdUsername(additionalDetailId, username);
	}

	@Override
	public List<AdditionalDetails> getAllAdditionalDetails() {

		return additionalDetailsDao.getAllAdditionalDetails();
	}

}
