package com.lff.reporting.util;

import java.util.List;

import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.MetricHeader;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.PivotHeader;
import com.google.api.services.analyticsreporting.v4.model.PivotHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.PivotValueRegion;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;
import com.google.common.collect.Lists;
import com.lff.reporting.util.ConsoleTable.ConsoleRow;

public class PrintConsoleFromResponse {
	public static void printResponse(GetReportsResponse response) {
		getConsoleTable(response).console();
	}
	
	public static ConsoleTable getConsoleTable(GetReportsResponse response){
		if (response.getReports().size() == 0) {
			System.out.println("No data found ");
			return null;
		}
		List<String> headers = Lists.newArrayList();

		Report reportForHeader = response.getReports().get(0);
		ColumnHeader header = reportForHeader.getColumnHeader();
		List<String> dimensionHeaders = header.getDimensions();
		for (String dimensionName : dimensionHeaders) {
			headers.add(dimensionName);
		}
		MetricHeader metricHeader = header.getMetricHeader();
		List<MetricHeaderEntry> metricHeaderEntries = metricHeader.getMetricHeaderEntries();
		for (MetricHeaderEntry metricHeaderEntry : metricHeaderEntries) {
			headers.add(metricHeaderEntry.getName());
		}
		List<PivotHeader> pivotHeaders = metricHeader.getPivotHeaders();
		if (pivotHeaders != null)
			for (PivotHeader pivotHeader : pivotHeaders) {
				for (PivotHeaderEntry pivotHeaderEntry : pivotHeader.getPivotHeaderEntries()) {
					headers.add(pivotHeaderEntry.getDimensionNames() + "." + pivotHeaderEntry.getDimensionValues());
				}
			}
		ConsoleRow consoleHeaderRow = new ConsoleRow(headers);
		List<ConsoleRow> consoleRows = Lists.newArrayList();
		for (Report report : response.getReports()) {
			List<ReportRow> rows = report.getData().getRows();
			if (rows != null)
				for (ReportRow row : rows) {
					List<String> cells = Lists.newArrayList();
					List<String> dimensions = row.getDimensions();
					for (String dimension : dimensions) {
						cells.add(dimension);
					}
					List<DateRangeValues> metrics = row.getMetrics();
					for (DateRangeValues metric : metrics) {
						for (String value : metric.getValues()) {
							cells.add(value);
						}
						List<PivotValueRegion> pivotValueRegions = metric.getPivotValueRegions();
						if(pivotValueRegions!=null)
							for (PivotValueRegion pivotValueRegion : pivotValueRegions) {
								for (String value : pivotValueRegion.getValues()) {
									cells.add(value);
								}
							}
					}
					consoleRows.add(new ConsoleRow(cells));
				}
		}
		return new ConsoleTable(consoleRows, consoleHeaderRow);
	}
}
