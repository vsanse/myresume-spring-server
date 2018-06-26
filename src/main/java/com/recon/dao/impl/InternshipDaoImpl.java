package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.InternshipDao;
import com.recon.entity.InternshipDetails;

import javassist.NotFoundException;

@Repository
@Transactional
public class InternshipDaoImpl implements InternshipDao {

	@PersistenceContext
	private EntityManager emg;

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Override
	public String addInternshipDetails(InternshipDetails interndetails) {
		emg.persist(interndetails);
		return "Success";
	}

	@Override
	public InternshipDetails updateInternshipDetails(InternshipDetails interndetails) throws NotFoundException {
		if (findByInternshipId(interndetails.getInternId()) == null) {
			throw new NotFoundException("item doesn't exists");
		}
		return emg.merge(interndetails);
	}

	@Override
	public int removeInternshipDetails(Long internId) {
		logger.info("inside delete user");
		InternshipDetails internship = findByInternshipId(internId);
		emg.remove(internship);
		return 1;
	}

	@Override
	public List<InternshipDetails> getAllInternshipDetails() {
		Query query = emg.createQuery("from InternshipDetails");
		return query.getResultList();
	}

	@Override
	public InternshipDetails findByInternshipId(Long internId) {
		return emg.find(InternshipDetails.class, internId);
	}

	@Override
	public List<InternshipDetails> getInternshipDetailsByUser(String username) {
		Query query = emg.createQuery("from InternshipDetails where userinfo.username like :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public InternshipDetails findbyInternshipIDandUsername(Long internId, String username) {
		Query query = emg
				.createQuery("from InternshipDetails where userinfo.username like :username and internId=:internID");
		try {
			return (InternshipDetails) query.setParameter("username", username).setParameter("internID", internId)
					.getSingleResult();
		} catch (RuntimeException e) {
			logger.error("ERROR NO RESULT FOUND");
			return null;
		}
	}

}
