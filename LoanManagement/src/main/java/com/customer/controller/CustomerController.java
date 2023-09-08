package com.customer.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.customer.orm.model.AUser;
import com.customer.orm.model.Customer;
import com.customer.orm.model.LoanApplicant;
import com.customer.orm.model.LoanApplicationForm;
import com.customer.orm.service.CustomerService;

@Controller
public class CustomerController {

	CustomerService lservice;

	@Autowired
	CustomerController(CustomerService loanserv) {
		this.lservice = loanserv;
	}

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public String adminPage(@Validated LoanApplicant l1, Model model) {
	
		List<LoanApplicant> l2 = lservice.getAllApplications();
		
		model.addAttribute("allApplicants", l2);

		System.out.println("\n\n");
		for (LoanApplicant i : l2) {
			System.out.println(i);
		}
		return "adminPage";
	}

	@RequestMapping(value = "/getApplicationForm", method = RequestMethod.GET)
	public String customerPage(Model model) {
		return "custApplication";
	}
	
	@RequestMapping(value = "/submitApplication", method = RequestMethod.GET)
	public String submitApplication(@Validated LoanApplicationForm l, Model model) throws ParseException {
		lservice.sendApplicationDetails(l);
		return "welcomePag";
	}
}
