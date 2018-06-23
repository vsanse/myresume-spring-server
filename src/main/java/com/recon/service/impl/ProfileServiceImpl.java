package com.recon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.dao.EducationDao;
import com.recon.dao.InternshipDao;
import com.recon.dao.UserDao;
import com.recon.model.Profile;
import com.recon.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private EducationDao edudao;
	
	@Autowired
	private InternshipDao interndao;
	
	@Override
	public Profile getProfileByUsername(String username) {
		Profile profile = new Profile();
		if(!userdao.isUsernameTaken(username))
			return null;
		profile.setUserinfo(userdao.findByUserName(username));
		profile.setEducationDetails(edudao.getEducationDetailsByUser(username));
		profile.setInternshipDetails(interndao.getInternshipDetailsByUser(username));
		return profile;
	}

	@Override
	public List<Profile> getAllProfiles() {
		List<String> usernames = userdao.getAllUsernames();
		List<Profile> profiles = new ArrayList<>();
		for(String username : usernames) {
			profiles.add(getProfileByUsername(username));
		}
		return profiles;
	}


	
	

}
