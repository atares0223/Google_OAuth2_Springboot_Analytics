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
import com.google.api.services.analyticsreporting.v4.model.OrderBy;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;

public class HistogramBucketsOfDimension extends ConsoleTableReport{

  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  public  GetReportsResponse getReport(AnalyticsReporting service,String viewId) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("2019-03-07");
    dateRange.setEndDate("2019-03-13");

    // Create the Metrics object.
    Metric sessions = new Metric()
        .setExpression("ga:sessions")
        .setAlias("sessions");
    
    Dimension sessionCount = new Dimension().setName("ga:sessionCount");
    sessionCount.setHistogramBuckets(Arrays.asList(new Long[]{1L,3L,5L,7L}));

    
    OrderBy sessionsOrderBy = new OrderBy();
    sessionsOrderBy.setFieldName("ga:sessionCount");
    sessionsOrderBy.setOrderType("HISTOGRAM_BUCKET");
    
    
    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(viewId)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(sessions))
        .setDimensions(Arrays.asList(sessionCount))
        .setOrderBys(Arrays.asList(sessionsOrderBy))
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