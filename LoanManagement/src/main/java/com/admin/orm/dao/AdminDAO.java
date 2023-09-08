package com.admin.orm.dao;

import java.util.List;

import com.admin.orm.model.Users;

public interface AdminDAO {
	
	List<Users> getAllusers();
	void saveUser(Users r);
}
