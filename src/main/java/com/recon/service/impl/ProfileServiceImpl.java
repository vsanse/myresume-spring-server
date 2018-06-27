package com.recon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recon.model.Profile;
import com.recon.service.EducationService;
import com.recon.service.InternshipService;
import com.recon.service.ProfileService;
import com.recon.service.ProjectService;
import com.recon.service.TrainingService;
import com.recon.service.UserService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserService userService;

	@Autowired
	private EducationService eduService;

	@Autowired
	private InternshipService internService;

	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private ProjectService projectService;

	@Override
	public Profile getProfileByUsername(String username) {
		Profile profile = new Profile();
		if (!userService.isUsernameTaken(username))
			return null;
		profile.setUserinfo(userService.findByUserName(username));
		profile.setEducationDetails(eduService.getEducationDetailsByUser(username));
		profile.setInternshipDetails(internService.getInternshipDetailsByUser(username));
		profile.setTrainingDetails(trainingService.getTrainingDetailsByUser(username));
		profile.setProjectDetails(projectService.getAllProjectsByUsername(username));
		return profile;
	}

	@Override
	public List<Profile> getAllProfiles() {
		List<String> usernames = userService.getAllUsernames();
		List<Profile> profiles = new ArrayList<>();
		for (String username : usernames) {
			profiles.add(getProfileByUsername(username));
		}
		return profiles;
	}

}
