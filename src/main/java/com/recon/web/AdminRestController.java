package com.recon.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.EducationDetails;
import com.recon.entity.InternshipDetails;
import com.recon.entity.UserInfo;
import com.recon.model.Profile;
import com.recon.service.EducationService;
import com.recon.service.InternshipService;
import com.recon.service.ProfileService;
import com.recon.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private EducationService eduService;

	@Autowired
	private InternshipService internService;

	@Autowired
	ProfileService profileservice;

	@RequestMapping(value = "/getallusers", method = RequestMethod.GET)
	public List<UserInfo> getAllUsers() {
		return userService.getAllUsers();

	}

	@RequestMapping(value = "/getalleducation", method = RequestMethod.GET)
	public List<EducationDetails> getAllEducationDetails() {
		return eduService.getAllEducationDetials();
	}

	@RequestMapping(value = "/getallinternships", method = RequestMethod.GET)
	public List<InternshipDetails> getAllInternshipDetails() {
		return internService.getAllInternshipDetails();
	}

	@RequestMapping(value = "/getallusernames", method = RequestMethod.GET)
	public List<String> getAllUsername() {
		return userService.getAllUsernames();
	}

	@RequestMapping(value = "/getallprofiles", method = RequestMethod.GET)
	public List<Profile> getAllProfiles() {
		return profileservice.getAllProfiles();
	}
}
