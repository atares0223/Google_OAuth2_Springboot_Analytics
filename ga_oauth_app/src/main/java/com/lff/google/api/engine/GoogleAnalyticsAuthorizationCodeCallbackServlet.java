package com.lff.google.api.engine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.lff.global.X;

/**
 *  this is just copy from google sample , this is a way of google servlet , since i use the spring-boot , the controller have the end-points rather then the servlet end-points
 * @author ffliu
 *
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/google/oauth2callback")
@Deprecated
public class GoogleAnalyticsAuthorizationCodeCallbackServlet extends AbstractAuthorizationCodeCallbackServlet{
	@Override
	  protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
	      throws ServletException, IOException {
	    resp.sendRedirect(Utils.MAIN_SERVLET_PATH);
	  }

	  @Override
	  protected void onError(
	      HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
	      throws ServletException, IOException {
	    String nickname = "user";
	    resp.getWriter().print("<h3>Hey " + nickname + ", why don't you want to play with me?</h1>");
	    resp.setStatus(200);
	    resp.addHeader("Content-Type", "text/html");
	    return;
	  }

	  @Override
	  protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
	    return Utils.initializeFlow();
	  }

	  @Override
	  protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
	    return Utils.getRedirectUri(req);
	  }

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return (String) req.getSession().getAttribute(X.GOOGLE_ANALYTICS_USERID__NAME);
	}
	
	
}
