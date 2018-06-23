package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.InternshipDao;
import com.recon.entity.InternshipDetails;

@Repository
@Transactional
public class InternshipDaoImpl implements InternshipDao{

	@Autowired
	private EntityManager emg;
	@Override
	public String addInternshipDetails(InternshipDetails interndetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeInternshipDetails(Long internId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<InternshipDetails> getAllInternshipDetails() {
		Query query  = emg.createQuery("from InternshipDetails");
		return query.getResultList();
	}

	@Override
	public InternshipDetails findByInternshipId(Long internId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InternshipDetails> getInternshipDetailsByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
