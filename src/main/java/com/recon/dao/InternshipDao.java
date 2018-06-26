package com.recon.dao;

import java.util.List;

import com.recon.entity.InternshipDetails;

import javassist.NotFoundException;

public interface InternshipDao {
	public String addInternshipDetails(InternshipDetails interndetails);
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails) throws NotFoundException;
	public int removeInternshipDetails(Long internId);
	public List<InternshipDetails> getAllInternshipDetails();
	public InternshipDetails findByInternshipId(Long internId);
	public List<InternshipDetails> getInternshipDetailsByUser(String username);
	public InternshipDetails findbyInternshipIDandUsername(Long internId, String username);

}
