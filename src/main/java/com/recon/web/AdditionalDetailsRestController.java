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

import com.recon.entity.AdditionalDetails;
import com.recon.service.AdditionalDetailsService;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/additional")
@PropertySource("classpath:errorcodes.properties")
public class AdditionalDetailsRestController {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private AdditionalDetailsService additionalDetailsService;

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
	public List<AdditionalDetails> getAdditionalDetails(@RequestParam(value = "username") String username) {
		logger.debug("inside get additionalDetails details for {}", username);
		return additionalDetailsService.getAdditionalDetailsByUsername(username);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addAdditionalDetails(@RequestBody AdditionalDetails additionalDetail) {
		try {
			logger.info("Inside addAdditionalDetails");
			additionalDetailsService.addAdditionalDetails(additionalDetail);
			return new ResponseEntity<>(additionalDetail, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateAdditionalDetails(@RequestBody AdditionalDetails additionalDetail) {
		logger.info("inside update additionalDetails details");
		try {
			return new ResponseEntity<>(additionalDetailsService.updateAdditionalDetails(additionalDetail), HttpStatus.CREATED);
		} catch (RuntimeException | NotFoundException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "delete/{additionalDetailsId}", method = RequestMethod.GET)
	public ResponseEntity<?> removeEducationdetails(@PathVariable("additionalDetailsId") Long additionalDetailsId,
			HttpServletResponse response) {
		logger.debug("inside remove additionalDetails: {}", additionalDetailsId);
		String result = null;
		if (additionalDetailsService.getAdditionalDetailsByIdUsername(additionalDetailsId, userservice.getCurrentUser().getUserName()) == null) {
			return new ResponseEntity<>(
					new CustomErrorType(unauthorizedDeleteErrorCode, unauthorizedDeleteErrorMessage),
					HttpStatus.UNAUTHORIZED);
		}
		try {
			additionalDetailsService.removeAdditionalDetails(additionalDetailsId);
			result = "{\"deleted\":true}";
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (RuntimeException e) {
			logger.debug("EXCEPTION: {}", e.getMessage());
			result = "{\"deleted\":false}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}
}
