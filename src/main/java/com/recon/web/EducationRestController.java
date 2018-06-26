package com.recon.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.EducationDetails;
import com.recon.service.EducationService;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

@RestController
@RequestMapping("/education")
@PropertySource("classpath:errorcodes.properties")
public class EducationRestController {

	private Logger logger = LoggerFactory.getLogger("myresume");
	
	@Autowired
	private EducationService eduservice;
	
	@Autowired
	private UserService userservice;
	
	@Value("${unauthorizedDeleteErrorCode}")
	private String unauthorizedDeleteErrorCode;
	
	@Value("${unauthorizedDeleteErrorMessage}")
	private String unauthorizedDeleteErrorMessage;
	
	@Value("${invalidDataErrorCode}")
	private String invalidDataErrorCode;
	
	@Value("${invalidDataErrorMessage}")
	private String invalidDataErrorMessage;
	
	@RequestMapping(value="/getdetails",method = RequestMethod.GET)
	public List<EducationDetails> getEducationDetails(@RequestParam(value="username") String username){
		return eduservice.getEducationDetailsByUser(username);
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<?> addEducationDetails(@RequestBody EducationDetails edu){
		try{
		logger.info("Inside addEducationDetails");
		eduservice.addEducationDetails(edu);
		return new ResponseEntity<>(edu, HttpStatus.CREATED);
		}
		catch(RuntimeException e){
			logger.debug(e.getMessage());
			return new ResponseEntity< >(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<?> updateEducationDetails(@RequestBody EducationDetails edu){
		logger.info("inside update education details");
		try{
		return new ResponseEntity<>(eduservice.updateEducationDetails(edu),HttpStatus.CREATED);
		}
		catch(RuntimeException | NotFoundException e){
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage), HttpStatus.BAD_REQUEST);
			}
	}
	
	@RequestMapping(value="delete/{educationId}",method=RequestMethod.GET)
	public ResponseEntity<?> removeEducationdetails(@PathVariable("educationId") Long educationId,HttpServletResponse response){
		logger.debug("inside remove edu: {}");
		String result=null;
		if(eduservice.findbyEduIDandUsername(educationId, userservice.getCurrentUser().getUserName())==null) {
			return new ResponseEntity<>(new CustomErrorType(unauthorizedDeleteErrorCode, unauthorizedDeleteErrorMessage),HttpStatus.UNAUTHORIZED);
		}
		try{
			eduservice.removeEducationDetails(educationId);
			result="{\"deleted\":true}";
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		catch(RuntimeException e){
			logger.debug("EXCEPTION: {}",e.getMessage());
			result="{\"deleted\":false}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}
}
