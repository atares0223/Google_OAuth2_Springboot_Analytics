package com.lff.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.Cohort;
import com.google.api.services.analyticsreporting.v4.model.CohortGroup;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;

public class CohortReport extends ConsoleTableReport{
	/**
	 * Queries the Analytics Reporting API V4.
	 *
	 * @param service
	 *            An authorized Analytics Reporting API V4 service object.
	 * @return GetReportResponse The Analytics Reporting API V4 response.`
	 * @throws IOException
	 */
	public GetReportsResponse getReport(AnalyticsReporting service,String viewID) throws IOException {

		// Create the Metrics object.
		Metric cohortPageviewsPerUserMetric = new Metric().setExpression("ga:cohortPageviewsPerUser").setAlias("cohortPageviewsPerUser");
		Metric cohortTotalUsersMetric = new Metric().setExpression("ga:cohortTotalUsers").setAlias("cohortTotalUsers");
		Metric cohortActiveUsersMetric = new Metric().setExpression("ga:cohortActiveUsers").setAlias("cohortActiveUsers");
		Metric cohortSessionsPerUserMetric = new Metric().setExpression("ga:cohortSessionsPerUser").setAlias("ga:cohortSessionsPerUser");

		Dimension cohortDimension = new Dimension().setName("ga:cohort");
		Dimension cohortNthDayDimension = new Dimension().setName("ga:cohortNthDay");
		CohortGroup cohortGroup = new CohortGroup();
		Cohort cohort1 = new Cohort();
		cohort1.setName("cohort1");
		cohort1.setType("FIRST_VISIT_DATE");
		DateRange dateRange1 = new DateRange();
		dateRange1.setStartDate("2019-03-07");
		dateRange1.setEndDate("2019-03-07");
		cohort1.setDateRange(dateRange1);
		
		Cohort cohort2 = new Cohort();
		cohort2.setName("cohort2");
		cohort2.setType("FIRST_VISIT_DATE");
		DateRange dateRange2 = new DateRange();
		dateRange2.setStartDate("2019-03-09");
		dateRange2.setEndDate("2019-03-09");
		cohort2.setDateRange(dateRange2);
		cohortGroup.setCohorts(Arrays.asList(cohort1,cohort2));

		// Create the ReportRequest object.
		ReportRequest request = new ReportRequest().setViewId(viewID)
				.setMetrics(Arrays.asList(cohortActiveUsersMetric,cohortTotalUsersMetric,cohortPageviewsPerUserMetric,cohortSessionsPerUserMetric)).setDimensions(Arrays.asList(cohortDimension,cohortNthDayDimension))
				.setCohortGroup(cohortGroup)
				;
		ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
		requests.add(request);

		// Create the GetReportsRequest object.
		GetReportsRequest getReport = new GetReportsRequest().setReportRequests(requests);

		// Call the batchGet method.
		GetReportsResponse response = service.reports().batchGet(getReport).execute();

		// Return the response.
		return response;
	}

	

}