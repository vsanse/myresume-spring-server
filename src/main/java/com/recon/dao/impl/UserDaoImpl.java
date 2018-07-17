package com.recon.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.recon.dao.UserDao;
import com.recon.entity.UserInfo;

import javassist.NotFoundException;

@Repository("userdao")
@Transactional
public class UserDaoImpl implements UserDao {
	private Logger logger = LoggerFactory.getLogger("myresume");

	@PersistenceContext
	private EntityManager emg;

	@Override
	public UserInfo findByUserName(String userName) {
		if (userName != null) {
			return emg.find(UserInfo.class, userName);
		}
		return null;
	}

	@Override
	public UserInfo findByUserEmail(String email) {
		Query query = emg.createQuery("from UserInfo where email like :email");
		try {
			return (UserInfo) query.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public String insertUser(UserInfo user) {
		logger.info("inside insert User:{}", user.toString());
		emg.persist(user);
		return "Success";
	}

	@Override
	public UserInfo update(UserInfo user) throws NotFoundException {
		logger.info("inside update user");
		if(findByUserName(user.getUserName())== null){
			throw new NotFoundException("item doesn't exists");
		}
		return emg.merge(user);
	}

	@Override
	public int deleteUser(String userName) {
		logger.info("inside delete user");
		UserInfo user = findByUserName(userName);
		emg.remove(user);
		return 1;

	}

	@Override
	public List<UserInfo> getAllUsers() {
		Query query = emg.createQuery("from UserInfo");
		logger.debug("Results: {}", query.getResultList());
		return query.getResultList();
	}

	@Override
	public boolean isUsernameTaken(String username) {
		logger.info("Checking User Name");
		if (findByUserName(username) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmailAreadyRegistered(String email) {
		if (findByUserEmail(email) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> searchUser(String serachCriteria) {
		logger.debug("Search Criteria Before: {}",serachCriteria);
		String[] criteria = serachCriteria.split("\\s+");
		logger.debug("Search Criteria: {}",criteria[0]);
		Query query = emg.createQuery("Select username from UserInfo where firstName in :fname or lastName in :lname or email in :emails or username in :usernames");
		query.setParameter("fname", Arrays.asList(criteria))
			 .setParameter("lname",  Arrays.asList(criteria))
			 .setParameter("emails",  Arrays.asList(criteria))
			 .setParameter("usernames",  Arrays.asList(criteria));
		return query.getResultList();
	}

	@Override
	public List<String> getAllUsernames() {
		Query query = emg.createQuery("SELECT username from UserInfo");
		return query.getResultList();
	}
}
