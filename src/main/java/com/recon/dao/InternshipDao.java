package com.recon.dao;

import java.util.List;

import com.recon.entity.InternshipDetails;

public interface InternshipDao {
	public String addInternshipDetails(InternshipDetails interndetails);
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails);
	public int removeInternshipDetails(Long internId);
	public List<InternshipDetails> getAllInternshipDetails();
	public InternshipDetails findByInternshipId(Long internId);
	public InternshipDetails getInternshipDetailsByUser(String username);

}
