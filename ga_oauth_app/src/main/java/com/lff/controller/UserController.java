package com.lff.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lff.exception.GenericServiceException;
import com.lff.exception.sql.ObjectNotFoundException;
import com.lff.exception.validate.FormValidateException;
import com.lff.global.X;
import com.lff.model.User;
import com.lff.model.response.AppResponse;
import com.lff.model.response.AppResponse.AppResponseStatusCodeEnum;
import com.lff.service.UserService;
import com.lff.util.GoogleAnalyticsUserIdUtil;
import com.lff.vo.LoginVO;
import com.lff.vo.RegisterVO;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	/*
	 * private static final AppEngineDataStoreFactory DATA_STORE_FACTORY =
	 * AppEngineDataStoreFactory.getDefaultInstance();
	 */

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	@ResponseBody
	public AppResponse register(RegisterVO registerVO) {
		String msg = userService.register(registerVO);
		if (msg == null) {
			return new AppResponse(AppResponseStatusCodeEnum.Sucess.getStatusCode(), null);
		}
		;
		return new AppResponse(AppResponseStatusCodeEnum.Error.getStatusCode(), msg);
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	@ResponseBody
	public AppResponse login(LoginVO loginVO, HttpSession session) {
		User user;
		try {
			user = userService.login(loginVO);
			session.setAttribute(X.USER_SESSION_NAME, user);
			session.setAttribute(X.GOOGLE_ANALYTICS_USERID__NAME,
					GoogleAnalyticsUserIdUtil.toGoogleAnalyticsUserId(user.getId()));
		} catch (FormValidateException | GenericServiceException | ObjectNotFoundException e) {
			return new AppResponse(AppResponseStatusCodeEnum.Error.getStatusCode(), e.getMessage());
		}
		return new AppResponse(AppResponseStatusCodeEnum.Sucess.getStatusCode(), null);

	}

	@RequestMapping(path = "/guest", method = RequestMethod.GET)
	public String guest(HttpSession session) {
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			session.removeAttribute(attributeNames.nextElement());
		}
		return "seller/list";

	}

}
