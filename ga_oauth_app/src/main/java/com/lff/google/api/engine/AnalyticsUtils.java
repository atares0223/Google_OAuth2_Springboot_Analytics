package com.lff.google.api.engine;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

public class AnalyticsUtils {
	 static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	 private final static  String APPLICATION_NAME = "analytics_api_app";
	 public static Analytics initializeAnalytics(Credential credential) throws GeneralSecurityException, IOException  {
		
		  HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		  
		    // Set up and return Google Analytics API client.
		    return new Analytics.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(
		        APPLICATION_NAME).build();
		  }
	 
	 public static String getFirstProfileId(Analytics analytics) throws IOException {
		    String profileId = null;

		    // Query accounts collection.
		    Accounts accounts = analytics.management().accounts().list().execute();

		    if (accounts.getItems().isEmpty()) {
		      System.err.println("No accounts found");
		    } else {
		      String firstAccountId = accounts.getItems().get(0).getId();
		      if(firstAccountId.equals("54516992"))
		    	  firstAccountId = accounts.getItems().get(1).getId();

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
}
