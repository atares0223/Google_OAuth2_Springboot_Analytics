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

public class EventCategoryReporting extends ConsoleTableReport{
  

  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  public  GetReportsResponse getReport(AnalyticsReporting service,String viewID) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("2016-03-07");
    dateRange.setEndDate("2019-03-13");

    // Create the Metrics object.
    Metric totalEventsMetric = new Metric().setExpression("ga:totalEvents").setAlias("totalEvents");
    Metric uniqueEventsMetric = new Metric().setExpression("ga:uniqueEvents").setAlias("uniqueEvents");
    Metric avgEventValueMetric = new Metric().setExpression("ga:avgEventValue").setAlias("avgEventValue");
    Metric eventValueMetric = new Metric().setExpression("ga:eventValue").setAlias("eventValue");
    Metric sessionsWithEventMetric = new Metric().setExpression("ga:sessionsWithEvent").setAlias("sessionsWithEvent");
    Metric eventsPerSessionWithEventMetric = new Metric().setExpression("ga:eventsPerSessionWithEvent").setAlias("eventsPerSessionWithEvent");
    
    
    Dimension eventCategoryDimension = new Dimension().setName("ga:eventCategory");
    Dimension eventActionDimension = new Dimension().setName("ga:eventAction");
    Dimension eventLabelDimension = new Dimension().setName("ga:eventLabel");
    
    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(viewID)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(totalEventsMetric,uniqueEventsMetric,avgEventValueMetric,eventValueMetric,sessionsWithEventMetric,eventsPerSessionWithEventMetric))
        .setDimensions(Arrays.asList(eventCategoryDimension,eventActionDimension,eventLabelDimension))
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