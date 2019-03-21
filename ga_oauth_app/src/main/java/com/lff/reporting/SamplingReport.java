package com.lff.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;

public class SamplingReport extends ConsoleTableReport{
 
  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  public  GetReportsResponse getReport(AnalyticsReporting service, String viewId) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("1DaysAgo");
    dateRange.setEndDate("today");

    // Create the Metrics object.
    Metric sessionsMetric = new Metric()
        .setExpression("ga:sessions")
        .setAlias("sessions");
    
    Dimension mediumDimension = new Dimension().setName("ga:medium");
    
    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(viewId)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(sessionsMetric))
        .setDimensions(Arrays.asList(mediumDimension))
        .setSamplingLevel("SMALL")
        ;

    ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
    requests.add(request);

    // Create the GetReportsRequest object.
    GetReportsRequest getReport = new GetReportsRequest()
        .setReportRequests(requests);

    // Call the batchGet method.
    GetReportsResponse response = service.reports().batchGet(getReport).execute();

    // Return the response.
    return response;
  }

}