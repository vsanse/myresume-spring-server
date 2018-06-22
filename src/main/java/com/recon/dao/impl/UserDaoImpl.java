package com.recon.dao.impl;

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

@Repository("userdao")
@Transactional

public class UserDaoImpl implements UserDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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

		try {
			emg.persist(user);
			return "Success";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "Invalid Data Recieved";
		}
	}

	@Override
	public UserInfo update(UserInfo user) {
		logger.info("inside update user");
		return emg.merge(user);
	}

	@Override
	public int deleteUser(String userName) {
		logger.info("inside delete user");
		try {
			UserInfo user = findByUserName(userName);
			emg.remove(user);
			return 1;
		} catch (Exception e) {
			logger.error("[ERROR] {}", e.getMessage());
			return 0;
		}
	}

	@Override
	public List<UserInfo> getAllUsers() {
		Query query = emg.createQuery("from UserInfo");
		logger.debug("Results: {}", query.getResultList());
		return query.getResultList();
	}

	@Override
	public boolean isUsernameTaken(String username) {
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
}
