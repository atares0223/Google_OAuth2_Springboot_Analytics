package com.lff.google.api.engine;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.lff.global.X;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/google/oauth")
/**
 * this is just copy from google sample , this is a way of google servlet , since i use the spring-boot , the controller have the end-points rather then the servlet end-points
 * @author ffliu
 *
 */
@Deprecated
public class GoogleAnalyticsAuthorizationCodeServlet extends AbstractAuthorizationCodeServlet {
	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		return Utils.initializeFlow();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		return Utils.getRedirectUri(req);
//		return null;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AuthorizationCodeFlow authFlow = initializeFlow();
		Credential credential = authFlow.loadCredential(getUserId(req));
	    try {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			System.out.println(getFirstProfileId(analytics));
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	   
	}
	
	
	 
	 private static String getFirstProfileId(Analytics analytics) throws IOException {
		    String profileId = null;

		    // Query accounts collection.
		    Accounts accounts = analytics.management().accounts().list().execute();

		    if (accounts.getItems().isEmpty()) {
		      System.err.println("No accounts found");
		    } else {
		      String firstAccountId = accounts.getItems().get(0).getId();

		      // Query webproperties collection.
		      Webproperties webproperties =
		          analytics.management().webproperties().list(firstAccountId).execute();

		      if (webproperties.getItems().isEmpty()) {
		        System.err.println("No Webproperties found");
		      } else {
		        String firstWebpropertyId = webproperties.getItems().get(0).getId();

		        // Query profiles collection.
		        Profiles profiles =
		            analytics.management().profiles().list(firstAccountId, firstWebpropertyId).execute();

		        if (profiles.getItems().isEmpty()) {
		          System.err.println("No profiles found");
		        } else {
		          profileId = profiles.getItems().get(0).getId();
		        }
		      }
		    }
		    return profileId;
		  }
	 
	 @Override
	  protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return (String) req.getSession().getAttribute(X.GOOGLE_ANALYTICS_USERID__NAME);
	  }
	 
	/* @Override
	  protected AuthorizationCodeFlow initializeFlow() throws IOException {
		 Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
		 credential.setExpiresInSeconds(expiresIn);
		 return new GoogleAuthorizationCodeFlow.Builder(
			        new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
			        "362393542336-ovijrqom66171i81hlro5t3c48imdkqv.apps.googleusercontent.com", "DEZCpS_v2CpCn-RnW_kwDYik",
			        Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
			        DATA_STORE_FACTORY).setAccessType("offline").build();
		 return new GoogleAuthorizationCodeFlow.Builder(
			        HTTP_TRANSPORT, JSON_FACTORY, getClientSecrets(), SCOPES).setDataStoreFactory(
			        DATA_STORE_FACTORY).setAccessType("offline").build();
	    return new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
	        new NetHttpTransport(),
	        new JacksonFactory(),
	        new GenericUrl("https://server.example.com/token"),
	        new BasicAuthentication("s6BhdRkqt3", "7Fjfp0ZBr1KtDRbnfVdmIw"),
	        "s6BhdRkqt3",
	        "https://server.example.com/authorize").setCredentialDataStore(
	            StoredCredential.getDefaultDataStore(
	                new FileDataStoreFactory(new File("datastoredir"))))
	        .build();
	  }*/

}
