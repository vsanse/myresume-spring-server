package com.recon.service;

import java.util.List;

import com.recon.entity.AdditionalDetails;

import javassist.NotFoundException;

public interface AdditionalDetailsService {
	
	public void addAdditionalDetails(AdditionalDetails additionalDetail);

	public void removeAdditionalDetails(Long additionalDetailId);

	public AdditionalDetails updateAdditionalDetails(AdditionalDetails additionalDetail) throws NotFoundException;

	public List<AdditionalDetails> getAdditionalDetailsByUsername(String username);

	public AdditionalDetails getAdditionalDetailsById(Long Id);

	public AdditionalDetails getAdditionalDetailsByIdUsername(Long additionalDetailId, String username);

	public List<AdditionalDetails> getAllAdditionalDetails();
}
