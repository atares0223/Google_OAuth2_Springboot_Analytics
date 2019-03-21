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

public class HelloAnalyticsReporting extends ConsoleTableReport{
  


  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  public   GetReportsResponse getReport(AnalyticsReporting service,String viewID) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("2016-03-01");
    dateRange.setEndDate("2019-03-19");

    // Create the Metrics object.
    /*Metric sessions = new Metric()
        .setExpression("ga:sessions")
        .setAlias("sessions");
    Metric sessionDuration = new Metric().setExpression("ga:avgSessionDuration").setAlias("Avg. Session Duration");
    Metric pagesPerSession = new Metric().setExpression("ga:pageviewsPerSession").setAlias("Pages / Session");*/
    Metric pageviews = new Metric().setExpression("ga:pageviews").setAlias("pageViews");
  /*  MetricFilterClause metricFilterClause = new MetricFilterClause();
    MetricFilter GTMetricSessionsFilter = new MetricFilter();
    GTMetricSessionsFilter.setMetricName("ga:sessions");
    GTMetricSessionsFilter.setOperator("GREATER_THAN");
    GTMetricSessionsFilter.setComparisonValue("1");
    MetricFilter LTMetricSessionsFilter = new MetricFilter();
    LTMetricSessionsFilter.setMetricName("ga:sessions");
    LTMetricSessionsFilter.setOperator("LESS_THAN");
    LTMetricSessionsFilter.setComparisonValue("3");*/
    
  /*  MetricFilter avgSessionDurationFilter = new MetricFilter();
    avgSessionDurationFilter.setMetricName("ga:avgSessionDuration");
    avgSessionDurationFilter.setOperator("GREATER_THAN");
    avgSessionDurationFilter.setComparisonValue("0");*/
    /**
     * way 1 
     * use a single metricFilterClause (AND) and two filters.
     */
    /*metricFilterClause.setOperator("AND");
    metricFilterClause.setFilters(Arrays.asList(LTMetricSessionsFilter,avgSessionDurationFilter));*/
    
    /**
     * way2
     * use two metricFilterClauses each with a single filter
     */
/*    MetricFilterClause avgSessionDurationFilterClause = new MetricFilterClause();
    metricFilterClause.setFilters(Arrays.asList(GTMetricSessionsFilter));
    avgSessionDurationFilterClause.setFilters(Arrays.asList(avgSessionDurationFilter));*/
    
   
    
    
    /*Dimension pageTitle = new Dimension().setName("ga:pageTitle");
    Dimension landingPage = new Dimension().setName("ga:landingPagePath");*/
    Dimension page = new Dimension().setName("ga:pagePath");

    
    OrderBy sessionsOrderBy = new OrderBy();
    sessionsOrderBy.setFieldName("ga:sessions");
    sessionsOrderBy.setSortOrder("ASCENDING");
    
    OrderBy avgSessionDurationOrderBy = new OrderBy();
    avgSessionDurationOrderBy.setFieldName("ga:avgSessionDuration");
    avgSessionDurationOrderBy.setSortOrder("ASCENDING");
    
    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(viewID)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(pageviews))
        /**
         * way 1
         */
       // .setMetricFilterClauses(Arrays.asList(metricFilterClause))
        /**
         * way 2
         */
       // .setMetricFilterClauses(Arrays.asList(metricFilterClause,avgSessionDurationFilterClause))
        .setDimensions(Arrays.asList(page))
        //.setOrderBys(Arrays.asList(sessionsOrderBy,avgSessionDurationOrderBy))
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