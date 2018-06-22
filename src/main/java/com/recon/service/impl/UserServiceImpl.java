package com.recon.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recon.dao.UserDao;
import com.recon.entity.UserInfo;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserDao userdao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public void insertUser(UserInfo user) {
		UserInfo newuser = new UserInfo();
		newuser.setCurrentOrganization(user.getCurrentOrganization());
		newuser.setDesignation(user.getDesignation());
		newuser.setEmail(user.getEmail());
		newuser.setFirstName(user.getFirstName());
		newuser.setGithubLink(user.getGithubLink());
		newuser.setLastName(user.getLastName());
		newuser.setLinkedinLink(user.getLinkedinLink());
		newuser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newuser.setPhoneNumber(user.getPhoneNumber());
		newuser.setUserName(user.getUserName());
		userdao.insertUser(newuser);
	}

	@Override
	public UserInfo update(UserInfo user) {
		return userdao.update(user);
	}

	@Override
	public List<UserInfo> getAllUsers() {
		return userdao.getAllUsers();
	}

	@Override
	public UserInfo findByUserName(String userName) {
		return userdao.findByUserName(userName);
	}

	@Override
	public int deleteUser(String userName) {
		return userdao.deleteUser(userName);
	}

	@Override
	public UserInfo findByUserEmail(String email) {
		return userdao.findByUserEmail(email);

	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo user = userdao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	@Override
	public boolean isUsernameTaken(String username) {
		return userdao.isUsernameTaken(username);
	}

	@Override
	public boolean isEmailAreadyRegistered(String email) {
		// TODO Auto-generated method stub
		return userdao.isEmailAreadyRegistered(email);
	}
}
