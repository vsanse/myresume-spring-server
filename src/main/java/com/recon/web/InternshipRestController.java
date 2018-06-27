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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.InternshipDetails;
import com.recon.service.InternshipService;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/internship")
@PropertySource("classpath:errorcodes.properties")
public class InternshipRestController {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private InternshipService internshipservice;

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

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<InternshipDetails> getInternshipDetails(@RequestParam(value = "username") String username) {
		logger.debug("inside get internship details for {}", username);
		return internshipservice.getInternshipDetailsByUser(username);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addInternshipDetails(@RequestBody InternshipDetails interndetails) {
		try {
			logger.info("Inside addInternshipDetails");
			internshipservice.addInternshipDetails(interndetails);
			return new ResponseEntity<>(interndetails, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateInternshipDetails(@RequestBody InternshipDetails internship) {
		logger.info("inside update internship details");
		try {
			return new ResponseEntity<>(internshipservice.updateInternshipDetails(internship), HttpStatus.CREATED);
		} catch (RuntimeException | NotFoundException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "delete/{internshipId}", method = RequestMethod.GET)
	public ResponseEntity<?> removeEducationdetails(@PathVariable("internshipId") Long internshipId,
			HttpServletResponse response) {
		logger.debug("inside remove internship: {}", internshipId);
		String result = null;
		if (internshipservice.findbyInternshipIDandUsername(internshipId,
				userservice.getCurrentUser().getUserName()) == null) {
			return new ResponseEntity<>(
					new CustomErrorType(unauthorizedDeleteErrorCode, unauthorizedDeleteErrorMessage),
					HttpStatus.UNAUTHORIZED);
		}
		try {
			internshipservice.removeInternshipDetails(internshipId);
			result = "{\"deleted\":true}";
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (RuntimeException e) {
			logger.debug("EXCEPTION: {}", e.getMessage());
			result = "{\"deleted\":false}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}
}
