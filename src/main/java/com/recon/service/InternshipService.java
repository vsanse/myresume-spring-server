package com.recon.service;

import java.util.List;

import com.recon.entity.InternshipDetails;

import javassist.NotFoundException;

public interface InternshipService {
	
	public String addInternshipDetails(InternshipDetails interndetails);
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails) throws NotFoundException;
	public int removeInternshipDetails(Long internId);
	public List<InternshipDetails> getAllInternshipDetails();
	public InternshipDetails findByInternshipId(Long internId);
	public List<InternshipDetails> getInternshipDetailsByUser(String username);
	public InternshipDetails findbyInternshipIDandUsername(Long internshipId, String username);

}
