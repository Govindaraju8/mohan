package com.admin.orm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.admin.orm.dao.AdminDAO;
import com.admin.orm.model.RegisterUser;
import com.admin.orm.model.Users;
import com.admin.orm.model.ValidateUser;

@Component
public class AdminService {

	AdminDAO adao;

	Calendar calendar = Calendar.getInstance();

	String todayDt = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
			+ calendar.get(Calendar.DAY_OF_MONTH) + "";

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public AdminService(AdminDAO adminDao) {
		this.adao = adminDao;
	}

	public String ValidateUserDetails(ValidateUser v) {
		List<Users> l = adao.getAllusers(); // Getting all users
		String pwd = v.getPassword().hashCode()+"";
		for(Users i : l) {
			if(v.getUserName().equals(i.getUsername())) {
				if(pwd.equals(i.getPassword()) && i.getUserType().equals("ADMN")) {
					return "adminHomePage";
				}
				else if(pwd.equals(i.getPassword()) && i.getUserType().equals("CUST")) {
					return "custApplication";
				}
				else {
					return "Error";
				}
			}
		}
		return "Error";
	}

	@Transactional
	public void createUser(RegisterUser r) throws NumberFormatException, ParseException {
		String password = r.getPassword().hashCode()+"";
		Users u = new Users(r.getuserName(), dateFormat.parse(todayDt), r.getAccType(), password, r.getEmailId(),
				r.getRecEmailId(), Long.parseLong(r.getPhNum()));
		adao.saveUser(u);
	}
}
