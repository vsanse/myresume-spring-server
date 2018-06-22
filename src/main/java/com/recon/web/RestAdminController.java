package com.recon.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recon.entity.UserInfo;
import com.recon.service.UserService;

@RestController
@RequestMapping("/admin")
public class RestAdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getallusers",method = RequestMethod.GET)
	public List<UserInfo> getAllUsers(){
		return userService.getAllUsers();
		
	}
}
