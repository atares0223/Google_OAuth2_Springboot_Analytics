package com.lff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lff.dao.user.UserDao;
import com.lff.exception.GenericServiceException;
import com.lff.exception.sql.ObjectNotFoundException;
import com.lff.exception.validate.FormValidateException;
import com.lff.model.User;
import com.lff.vo.LoginVO;
import com.lff.vo.RegisterVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserDao userDao;

	public String register(RegisterVO registerVO) {
		String errormsg = null;
		if ((errormsg = checkRegisterVO(registerVO)) != null) {
			return errormsg;
		}
		User user = registerVO.convert();
		try {
			userDao.save(user);
		} catch (Exception e) {
			log.error("register fail : ", e);
			return "server error";
		}
		return null;
	}

	private String checkRegisterVO(RegisterVO registerVO) {
		if (registerVO.getEmail() == null)
			return "please type in email";
		if (registerVO.getName() == null)
			return "please type in name";
		if (registerVO.getPassword() == null)
			return "please type in password";
		return null;
	}

	public User login(LoginVO loginVO) throws FormValidateException, ObjectNotFoundException, GenericServiceException {

		checkLoginVO(loginVO);
		User user = loginVO.convert();
		User result;
		try {
			result = userDao.login(user);
		} catch (Exception e) {
			log.error("login fail : ", e);
			throw new GenericServiceException();
		}
		
		if (result == null)
			throw new ObjectNotFoundException("account not exist or password is not matched");
		return result;
	}

	private void checkLoginVO(LoginVO loginVO) throws FormValidateException {
		if (loginVO.getEmail() == null)
			throw new FormValidateException("email", "required");
		if (loginVO.getPassword() == null)
			throw new FormValidateException("password", "required");
	}
}
