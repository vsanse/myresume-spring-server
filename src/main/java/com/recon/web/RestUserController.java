package com.recon.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.recon.config.JwtTokenUtil;
import com.recon.entity.UserInfo;
import com.recon.model.AuthToken;
import com.recon.model.LoginUser;
import com.recon.service.UserService;
import com.recon.util.CustomErrorType;

@CrossOrigin
@RestController
@RequestMapping("/user")
@PropertySource("classpath:errorcodes.properties")
public class RestUserController {
	private Logger logger = LoggerFactory.getLogger("myresume");

	@Autowired
	private UserService userservice;

	@Value("${userExistsErrorCode}")
	private String userExistsErrorCode;
	
	@Value("${userExistsErrorMessage}")
	private String userExistsErrorMessage;
	
	@Value("${emailExistsErrorCode}")
	private String emailExistsErrorCode;
	
	@Value("${emailExistsErrorMessage}")
	private String emailExistsErrorMessage;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	
	
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserInfo user,UriComponentsBuilder ucBuilder ){
		logger.debug("USER [IN CONTROLLER]: {}",user.toString());
		if(userservice.isUsernameTaken(user.getUserName())== true) {
			logger.info("ERROR ! UserName Taken");
			return new ResponseEntity <CustomErrorType> (new CustomErrorType(userExistsErrorCode, userExistsErrorMessage),HttpStatus.CONFLICT);
		}
		if(userservice.isEmailAreadyRegistered(user.getEmail())== true){
			logger.info("ERROR ! Email Already Registered");
			return new ResponseEntity <CustomErrorType> (new CustomErrorType(emailExistsErrorCode, emailExistsErrorMessage),HttpStatus.CONFLICT);
		}
		userservice.insertUser(user);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/login").buildAndExpand(user.getUserName()).toUri());
        return new ResponseEntity<String>("{}", HttpStatus.CREATED);
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
		    logger.info("[Login creds:] {}",loginUser.toString());
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getUsername(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        final UserInfo user = userservice.findByUserName(loginUser.getUsername());
	        final String token = jwtTokenUtil.generateToken(user);
	        return ResponseEntity.ok(new AuthToken(token));
	    }
	 
	 @RequestMapping(value="/isUsernameAvailable",method = RequestMethod.GET)
	 public boolean isUsernameAvailable(@RequestParam(value="username") String username){
		 if(!userservice.isUsernameTaken(username)){
			 return true;
		 }
		 return false;
	 }
	 @RequestMapping(value="/isEmailRegistered",method = RequestMethod.GET)
	 public boolean isEmailRegistered(@RequestParam(value="email") String email){
		 if(userservice.isEmailAreadyRegistered(email)){
			 return true;
		 }
		 return false;
	 }
	
	 @RequestMapping(value="/me", method=RequestMethod.GET)
	 public UserInfo getCurrentUser(){	
		String username =  SecurityContextHolder.getContext().getAuthentication().getName();
		return userservice.findByUserName(username);
	 }
	
	
}
