package com.lff.dao.user;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lff.model.User;

@Repository
public class UserDao {
	@Autowired
	private SQLManager sqlManager;
	public void save(User user){
		sqlManager.insert(user);
	}
	
	public User login(User user){
		
		return sqlManager.query(User.class).andEq("email", user.getEmail()).andEq("password", user.getPassword()).single();
	}
	
}
