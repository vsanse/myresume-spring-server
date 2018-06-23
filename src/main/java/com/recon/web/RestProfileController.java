package com.recon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recon.model.Profile;
import com.recon.service.ProfileService;
import com.recon.util.CustomErrorType;

@RestController
@RequestMapping("/profile")
@PropertySource("classpath:errorcodes.properties")
public class RestProfileController {
	
	@Autowired
	private ProfileService profileservice;
	
	@Value("${noSuchUserExistsErrorCode}")
	private String noSuchUserExistsErrorCode;
	
	@Value("${noSuchUserExistsErrorMessage}")
	private String noSuchUserExistsErrorMessage;
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResponseEntity<?> getProfileByUsername(@RequestParam(value="username") String username) {
		Profile profile = profileservice.getProfileByUsername(username);
		if(profile==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorType(noSuchUserExistsErrorCode, noSuchUserExistsErrorMessage));
		return new ResponseEntity<>(profile, HttpStatus.OK);
			
	}
	
	
}
