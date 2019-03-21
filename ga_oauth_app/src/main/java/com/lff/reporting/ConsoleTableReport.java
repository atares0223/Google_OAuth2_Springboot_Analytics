package com.lff.reporting;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.lff.reporting.util.ConsoleTable;
import com.lff.reporting.util.PrintConsoleFromResponse;

public abstract class ConsoleTableReport {
	private static final String APPLICATION_NAME = "Hello Analytics Reporting";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//	private static final String KEY_FILE_LOCATION = "D:/google_analysitic/first-fuze-233108-42bcef62386e.json";

	public ConsoleTable getConsoleTable(Credential credential, String viewId)
			throws GeneralSecurityException, IOException {
		AnalyticsReporting service = initializeAnalyticsReporting(credential);
		// this is for demo , since this view id have the proper data 
		viewId = "191214818";
		GetReportsResponse response = getReport(service, viewId);
		return PrintConsoleFromResponse.getConsoleTable(response);
	}

	public  void print(Credential credential, String viewId) {
		try {
			getConsoleTable(credential, viewId).console();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes an Analytics Reporting API V4 service object.
	 *
	 * @return An authorized Analytics Reporting API V4 service object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private static AnalyticsReporting initializeAnalyticsReporting(Credential credential)
			throws GeneralSecurityException, IOException {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		/*
		 * GoogleCredential credential = GoogleCredential .fromStream(new
		 * FileInputStream(KEY_FILE_LOCATION))
		 * .createScoped(AnalyticsReportingScopes.all());
		 */

		// Construct the Analytics Reporting service object.
		return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}
	
	public abstract GetReportsResponse getReport(AnalyticsReporting service,String viewId) throws IOException ;
}
