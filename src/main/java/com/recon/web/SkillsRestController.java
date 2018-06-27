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

import com.recon.entity.SkillsDetails;
import com.recon.service.SkillsService;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/skills")
@PropertySource("classpath:errorcodes.properties")
public class SkillsRestController {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private SkillsService skillsService;

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
	public List<SkillsDetails> getSkillsDetails(@RequestParam(value = "username") String username) {
		logger.debug("inside get skills details for {}", username);
		return skillsService.getSkillsByUsername(username);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addSkillsDetails(@RequestBody SkillsDetails skillsdetails) {
		try {
			logger.info("Inside addSkillsDetails");
			skillsService.addSkills(skillsdetails);
			return new ResponseEntity<>(skillsdetails, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateSkillsDetails(@RequestBody SkillsDetails skill) {
		logger.info("inside update skills details");
		try {
			return new ResponseEntity<>(skillsService.updateSkill(skill), HttpStatus.CREATED);
		} catch (RuntimeException | NotFoundException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "delete/{skillsId}", method = RequestMethod.GET)
	public ResponseEntity<?> removeEducationdetails(@PathVariable("skillsId") Long skillsId,
			HttpServletResponse response) {
		logger.debug("inside remove skills: {}", skillsId);
		String result = null;
		if (skillsService.getSkillByIdUsername(skillsId, userservice.getCurrentUser().getUserName()) == null) {
			return new ResponseEntity<>(
					new CustomErrorType(unauthorizedDeleteErrorCode, unauthorizedDeleteErrorMessage),
					HttpStatus.UNAUTHORIZED);
		}
		try {
			skillsService.removeSkill(skillsId);
			result = "{\"deleted\":true}";
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (RuntimeException e) {
			logger.debug("EXCEPTION: {}", e.getMessage());
			result = "{\"deleted\":false}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}
}
