package com.customer.orm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.customer.orm.dao.CustomerDAO;
import com.customer.orm.model.AUser;
import com.customer.orm.model.Customer;
import com.customer.orm.model.LoanApplicant;
import com.customer.orm.model.LoanApplicationForm;

@Component
public class CustomerService {
	
	CustomerDAO LoanObj;
	
	@Autowired
	CustomerService(CustomerDAO loandao){
		this.LoanObj = loandao;
	}

	public List<LoanApplicant> getAllApplications() {
		return LoanObj.getAllApplicants();
	}
	
	@Transactional(readOnly = true)
	public List<AUser> getAllUser() {
		return LoanObj.getAllUser();
	}
	
	@Transactional
	public void sendApplicationDetails(LoanApplicationForm l) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		String todayDt = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// Inserting customer record
		Customer c = new Customer(l.getFirstName(), l.getLastName(), dateFormat.parse(l.getDateOfBirth()),
				l.getpANNumber(), l.getPhNumber(), l.getAddress(), l.getGurdName(), dateFormat.parse(todayDt), 1);
		sendCustomerDetails(c);
		
		// Inserting application record
		LoanApplicant lA = new LoanApplicant(8, dateFormat.parse(todayDt), l.getLnType(), l.getLnAmount(), 0.0, 0.0,
				l.getMonthsRequired(), l.getaIncome(), l.getdIncome(), 0.0, "XXXX", "Application Generated", 1,
				dateFormat.parse(todayDt));
		sendApplicationDetails(lA);
	}
	
	@Transactional
	public void sendCustomerDetails(Customer c) {
		LoanObj.saveCustomer(c);
	}
	
	@Transactional
	public void sendApplicationDetails(LoanApplicant lA) {
		LoanObj.saveApplication(lA);
	}
	
}
