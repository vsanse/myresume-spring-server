package com.recon.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.EducationDetails;
import com.recon.service.EducationService;

@RestController
@RequestMapping("/education")
public class RestEducationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EducationService eduservice;
	
	@RequestMapping(value="/getdetails",method = RequestMethod.GET)
	public List<EducationDetails> getEducationDetails(@RequestParam(value="username") String username){
		return eduservice.getAllEducationDetails(username);
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addEducationDetails(@RequestBody EducationDetails edu){
		logger.info("Inside addEducationDetails");
		eduservice.addEducationDetails(edu);
		return new ResponseEntity<>(edu, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<?> updateEducationDetails(@RequestBody EducationDetails edu){
		logger.info("inside update education details");
		return new ResponseEntity<>(eduservice.updateEducationDetails(edu),HttpStatus.CREATED);
	}
}
