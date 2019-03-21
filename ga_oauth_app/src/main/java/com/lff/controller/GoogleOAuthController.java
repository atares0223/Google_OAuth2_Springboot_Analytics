package com.lff.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.lff.global.X;
import com.lff.google.api.engine.Utils;

@Controller
@RequestMapping(path = "/google")
public class GoogleOAuthController {

	@RequestMapping(path = "/oauth2callback", method = RequestMethod.GET)
	public void oauthcallback(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		StringBuffer buf = req.getRequestURL();
	    if (req.getQueryString() != null) {
	      buf.append('?').append(req.getQueryString());
	    }
	    AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
	    String code = responseUrl.getCode();
	    
		if (code != null) {
			GoogleAuthorizationCodeFlow flow = Utils.initializeFlow();
			 /*GenericUrl requestUrl = new GenericUrl(req.getRequestURL().toString());
			    requestUrl.setRawPath(Utils.getStoreOAuth2TokenUriWithCurrentUri(req));*/
			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(Utils.getRedirectUri(req)).execute();
			String userId = (String) req.getSession().getAttribute(X.GOOGLE_ANALYTICS_USERID__NAME);
			if(userId == null)
				throw new Exception("No login user info");
		flow.createAndStoreCredential(response, userId);
		resp.sendRedirect((String) req.getSession().getAttribute(Utils.CALL_BACK_URI_ATT_KEY));
	    } else 
	      throw new Exception("Missing authorization code");
	}
}
