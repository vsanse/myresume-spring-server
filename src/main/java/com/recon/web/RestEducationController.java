package com.recon.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.EducationDetails;
import com.recon.service.EducationService;

@RestController
public class RestEducationController {

	@Autowired
	private EducationService eduservice;
	
	@RequestMapping(value="/geteducationdetails",method = RequestMethod.GET)
	public List<EducationDetails> getEducationDetails(@RequestParam(value="username") String username){
		return eduservice.getAllEducationDetails(username);
		
	}
}
