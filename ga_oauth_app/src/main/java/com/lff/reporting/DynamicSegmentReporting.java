package com.lff.reporting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.DynamicSegment;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.OrFiltersForSegment;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.Segment;
import com.google.api.services.analyticsreporting.v4.model.SegmentDefinition;
import com.google.api.services.analyticsreporting.v4.model.SegmentDimensionFilter;
import com.google.api.services.analyticsreporting.v4.model.SegmentFilter;
import com.google.api.services.analyticsreporting.v4.model.SegmentFilterClause;
import com.google.api.services.analyticsreporting.v4.model.SimpleSegment;

public class DynamicSegmentReporting extends ConsoleTableReport{


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
    Metric sessionsMetric = new Metric()
        .setExpression("ga:sessions")
        .setAlias("sessions");
    
    Dimension browserDimension = new Dimension().setName("ga:browser");
//    Dimension segmentDimension = new Dimension().setName("ga:segment");
   
    Segment segment = new Segment(); 
    DynamicSegment dynamicSegment = new DynamicSegment();
    dynamicSegment.setName("Sessions with Chrome browser");
    SegmentDefinition userSegment = new SegmentDefinition();
    SegmentFilter segmentFilter = new SegmentFilter();
    SimpleSegment simpleSegment = new SimpleSegment();
    segmentFilter.setSimpleSegment(simpleSegment);
    OrFiltersForSegment orFiltersForSegment = new OrFiltersForSegment();
    SegmentFilterClause segmentFilterClauses = new SegmentFilterClause();
    SegmentDimensionFilter dimensionFilter = new SegmentDimensionFilter();
    dimensionFilter.setOperator("EXACT");
    dimensionFilter.setDimensionName("ga:browser");
    dimensionFilter.setExpressions(Arrays.asList("Chrome"));
    segmentFilterClauses.setDimensionFilter(dimensionFilter);
    orFiltersForSegment.setSegmentFilterClauses(Arrays.asList(segmentFilterClauses));
    simpleSegment.setOrFiltersForSegment(Arrays.asList(orFiltersForSegment));
    userSegment.setSegmentFilters(Arrays.asList(segmentFilter));
    dynamicSegment.setUserSegment(userSegment);
    segment.setDynamicSegment(dynamicSegment);
    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(viewId)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(sessionsMetric))
        .setDimensions(Arrays.asList(browserDimension))
        //.setSegments(Arrays.asList(segment))
        ;
    request.toPrettyString();
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