package com.dfs.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dfs.spring.web.dao.FormValidationGroup;
import com.dfs.spring.web.dao.User;
import com.dfs.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
//		try {
			List<User> users = usersService.getAllUsers();
			
			model.addAttribute("users",users);
//		}catch(Exception e) {
//			System.out.println("Exception "+e.getClass());
//		}
		return "admin";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user",new User());
		return "newaccount";
	}
	

	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String doCreate(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Form does not validate");
			
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "newaccount";
		}
		else {
			user.setAuthority("ROLE_USER");
			user.setEnabled(true);
			System.out.println("Form validated");
			if(usersService.exists(user.getUsername())) {
				result.rejectValue("username", "DuplicateKey.user.username");
				return "newaccount";
				
			}
			try {
			usersService.create(user);
			}catch(DuplicateKeyException e) {
				result.rejectValue("username", "DuplicateKey.user.username","This username is already taken");
				return "newaccount";
				
			}
			
		}
		
		return "accountcreated";
	}
}
