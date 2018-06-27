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

import com.recon.entity.ProjectDetails;
import com.recon.service.ProjectService;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/project")
@PropertySource("classpath:errorcodes.properties")
public class ProjectRestController {

	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private ProjectService projectService;

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
	public List<ProjectDetails> getProjectDetails(@RequestParam(value = "username") String username) {
		logger.debug("inside get project details for {}", username);
		return projectService.getAllProjectsByUsername(username);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> addProjectDetails(@RequestBody ProjectDetails projectdetails) {
		try {
			logger.info("Inside addProjectDetails");
			projectService.addProjectDetails(projectdetails);
			return new ResponseEntity<>(projectdetails, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateProjectDetails(@RequestBody ProjectDetails project) {
		logger.info("inside update project details");
		try {
			return new ResponseEntity<>(projectService.updateProject(project), HttpStatus.CREATED);
		} catch (RuntimeException | NotFoundException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(new CustomErrorType(invalidDataErrorCode, invalidDataErrorMessage),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "delete/{projectId}", method = RequestMethod.GET)
	public ResponseEntity<?> removeEducationdetails(@PathVariable("projectId") Long projectId,
			HttpServletResponse response) {
		logger.debug("inside remove project: {}", projectId);
		String result = null;
		if (projectService.getProjectByUsernameandPid(userservice.getCurrentUser().getUserName(), projectId) == null) {
			return new ResponseEntity<>(
					new CustomErrorType(unauthorizedDeleteErrorCode, unauthorizedDeleteErrorMessage),
					HttpStatus.UNAUTHORIZED);
		}
		try {
			projectService.removeProjectDetails(projectId);
			result = "{\"deleted\":true}";
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (RuntimeException e) {
			logger.debug("EXCEPTION: {}", e.getMessage());
			result = "{\"deleted\":false}";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}
}
