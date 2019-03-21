package com.lff.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.analytics.Analytics;
import com.lff.google.api.engine.AnalyticsUtils;
import com.lff.google.api.engine.Utils;
import com.lff.reporting.CohortReport;
import com.lff.reporting.CustomDimensionAndMeticReporting;
import com.lff.reporting.DynamicSegmentReporting;
import com.lff.reporting.EventCategoryReporting;
import com.lff.reporting.HelloAnalyticsReporting;
import com.lff.reporting.HistogramBucketsOfDimension;
import com.lff.reporting.PivotTablesReport;
import com.lff.reporting.SegmentByIDReport;

@Controller
@RequestMapping(path = "/google/api")
public class GoogleApiController {

	/**
	 * the first view id for current user's google analytics account
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public String hello(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","hello");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new HelloAnalyticsReporting().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	
	
	
	@RequestMapping(path = "/cohort", method = RequestMethod.GET)
	public String cohort(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","cohort");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new CohortReport().getConsoleTable(credential, viewId));
			
		}
		return "/google_api/console";
	}
	
	
	@RequestMapping(path = "/custom_dimension_metric", method = RequestMethod.GET)
	public String customDimensionMetric(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","custom_dimension_metric");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new CustomDimensionAndMeticReporting().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	@RequestMapping(path = "/dynamit_segment", method = RequestMethod.GET)
	public String dynamitSegment(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","dynamit_segment");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new DynamicSegmentReporting().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	@RequestMapping(path = "/event_category", method = RequestMethod.GET)
	public String eventCategory(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","event_category");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new EventCategoryReporting().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	@RequestMapping(path = "/histogram_buckets", method = RequestMethod.GET)
	public String histogramBuckets(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","histogram_buckets");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new HistogramBucketsOfDimension().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	@RequestMapping(path = "/pivot_table", method = RequestMethod.GET)
	public String pivotTable(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","pivot_table");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new PivotTablesReport().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}
	
	@RequestMapping(path = "/segment_id", method = RequestMethod.GET)
	public String segmentId(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception {
		Credential credential = Utils.getCredential(req, resp);
		model.addAttribute("title","segment_id");
		if (credential != null) {
			Analytics analytics = AnalyticsUtils.initializeAnalytics(credential);
			String viewId = AnalyticsUtils.getFirstProfileId(analytics);
			model.addAttribute("consoleTable", new SegmentByIDReport().getConsoleTable(credential, viewId));
		}
		return "/google_api/console";
	}

}
