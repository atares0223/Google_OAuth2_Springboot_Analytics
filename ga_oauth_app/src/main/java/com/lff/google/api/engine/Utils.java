/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lff.google.api.engine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.analytics.AnalyticsScopes;
import com.lff.global.X;
import com.lff.util.SpringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	/**
	 * Global instance of the {@link DataStoreFactory}. The best practice is to
	 * make it a single globally shared instance across your application.
	 */

	private static GoogleClientSecrets clientSecrets = null;
	private static final Set<String> SCOPES = Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY);
	static final String USER_LOGIN = "/login.html";
	static final String MAIN_SERVLET_PATH = "/google/oauth";
	static final String AUTH_CALLBACK_SERVLET_PATH = "/google/oauth2callback";
	public static final String CALL_BACK_URI_ATT_KEY = "call_back_uri";
	static NetHttpTransport HTTP_TRANSPORT;
	static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static GoogleClientSecrets getClientSecrets() throws IOException {
		if (clientSecrets == null) {
			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
					new InputStreamReader(Utils.class.getResourceAsStream("/client_secrets.json")));
		}
		return clientSecrets;
	}

	private static NetHttpTransport getTransport() throws GeneralSecurityException, IOException {
		if (HTTP_TRANSPORT == null)
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		return HTTP_TRANSPORT;
	}

	public static GoogleAuthorizationCodeFlow initializeFlow() throws IOException {
		try {
			return new GoogleAuthorizationCodeFlow.Builder(getTransport(), JSON_FACTORY, getClientSecrets(), SCOPES)
					.setAccessType("offline")
					.setDataStoreFactory(new MysqlDataStoreFactory(SpringUtil.getBean(CredentialService.class)))
					.setApprovalPrompt("force")
					.build();
		} catch (GeneralSecurityException e) {
			log.error("Can not create newTrustedTransport", e);
			throw new IOException("Can not create newTrustedTransport");
		}
	}

	public static Credential getCredential(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		/**
		 * use google authorization flow .
		 * the flow have come code easy for us to redirect to google auth page
		 * e.g.: get the AuthorizationCodeRequestUrl.(the object used to come to goole oauth page)
		 * 		 get the 
		 */
		AuthorizationCodeFlow flow = Utils.initializeFlow();
		/**
		 * Login First!!
		 */
		String userId = (String) request.getSession().getAttribute(X.GOOGLE_ANALYTICS_USERID__NAME);
		if (userId == null){
			response.sendRedirect(USER_LOGIN);
			return null;
		}
			
		

		/**
		 * get the credential with myself implement of AbstractDataStore<StoredCredential>, the key is userId
		 * @see MysqlDataStore
		 * 
		 */
		Credential credential = flow.loadCredential(userId);
		if (credential != null && credential.getAccessToken() != null) {
			return credential;
		}
		
		request.getSession().setAttribute(CALL_BACK_URI_ATT_KEY, request.getRequestURI());
		/**
		 * can not get the credential. so come to google oAuth page
		 */
		AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl();
		authorizationUrl.setRedirectUri(getRedirectUri(request));
		response.sendRedirect(authorizationUrl.build());
		return null;
	}
	
	public static String getRedirectUri(HttpServletRequest req) {
		    GenericUrl requestUrl = new GenericUrl(req.getRequestURL().toString());
		    requestUrl.setRawPath(AUTH_CALLBACK_SERVLET_PATH);
		    return requestUrl.build();
		  }
	
	
	

}
