package com.recon.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recon.dao.AdditionalDetailsDao;
import com.recon.entity.AdditionalDetails;

import javassist.NotFoundException;

@Repository
@Transactional
public class AdditionalDetailsDaoImpl implements AdditionalDetailsDao {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private EntityManager emg;

	@Override
	public void addAdditionalDetails(AdditionalDetails additionalDetail) {
		emg.persist(additionalDetail);
	}

	@Override
	public void removeAdditionalDetails(Long additionalDetailId) {
		AdditionalDetails additionalDetail = getAdditionalDetailsById(additionalDetailId);
		if (additionalDetail != null) {
			emg.remove(additionalDetail);
		}
	}

	@Override
	public AdditionalDetails updateAdditionalDetails(AdditionalDetails additionalDetail) throws NotFoundException {
		if (getAdditionalDetailsById(additionalDetail.getAddid()) == null) {
			throw new NotFoundException("item doesn't exists");
		}
		return emg.merge(additionalDetail);
	}

	@Override
	public List<AdditionalDetails> getAdditionalDetailsByUsername(String username) {
		Query query = emg.createQuery("from AdditionalDetails where userinfo.username like :username")
				.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public AdditionalDetails getAdditionalDetailsById(Long Id) {
		return emg.find(AdditionalDetails.class, Id);
	}

	@Override
	public AdditionalDetails getAdditionalDetailsByIdUsername(Long additionalDetailId, String username) {
		try {
			Query query = emg.createQuery("from AdditionalDetails where username like :username and addId like :additionalDetailId")
					.setParameter("username", username).setParameter("additionalDetailId", additionalDetailId);
			return (AdditionalDetails) query.getSingleResult();
		} catch (RuntimeException e) {
			logger.error("NO SUCH RESULT FOUND");
			return null;
		}
	}

	@Override
	public List<AdditionalDetails> getAllAdditionalDetails() {
		return emg.createQuery("from AdditionalDetails").getResultList();
	}

}
