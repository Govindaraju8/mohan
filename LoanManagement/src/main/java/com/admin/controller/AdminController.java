package com.admin.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.admin.orm.model.RegisterUser;
import com.admin.orm.model.ValidateUser;
import com.admin.orm.service.AdminService;

@Controller
public class AdminController {
	
	AdminService aservice;
	
	@Autowired
	public AdminController(AdminService adminServ){
		this.aservice = adminServ;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authentication(@Validated ValidateUser vu, Model model) {
		String result = aservice.ValidateUserDetails(vu);
		return result;
	}

	@RequestMapping(value = "/redirectToRegistration", method = RequestMethod.GET)
	public String register() {
		return "registration";
	}

	@RequestMapping(value = "/redirectToLogin", method = RequestMethod.GET)
	public String LoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@Validated RegisterUser r) throws NumberFormatException, ParseException {
		aservice.createUser(r);
		return "registrationSuccess";
	}
}
