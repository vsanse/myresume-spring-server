package com.recon.dao;

import java.util.List;

import com.recon.entity.UserInfo;

import javassist.NotFoundException;

public interface UserDao {
	public String insertUser(UserInfo user);

	public UserInfo update(UserInfo user) throws NotFoundException;

	public List<UserInfo> getAllUsers();

	public UserInfo findByUserName(String userName);

	public int deleteUser(String userName);

	public UserInfo findByUserEmail(String email);

	public boolean isUsernameTaken(String username);

	public boolean isEmailAreadyRegistered(String email);

	public List<String> searchUser(String serachCriteria);

	public List<String> getAllUsernames();

}
