package com.recon.service;

import java.util.List;

import com.recon.entity.UserInfo;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

public interface UserService {
	public void insertUser(UserInfo user);

	public UserInfo update(UserInfo user) throws NotFoundException;

	public List<UserInfo> getAllUsers();

	public UserInfo findByUserName(String userName);

	public int deleteUser(String userName);

	public UserInfo findByUserEmail(String email);

	public boolean isUsernameTaken(String username);

	public boolean isEmailAreadyRegistered(String email);

	public UserInfo getCurrentUser();

	public List<String> searchUser(String searchCriteria);

	public List<String> getAllUsernames();

}
