package com.solutiondesign.controller;

import java.io.IOException;

import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.liferay.portal.util.PortalUtil;
import com.solutiondesign.exception.FeedPreferenceException;
import com.solutiondesign.service.FeedPreferenceService;

@RequestMapping("EDIT")
public class RssEditController {

	private static final String DISALLOW = "Disallow user feeds";
	private static final String ALLOW = "Allow user feeds";
	
	@Autowired
	FeedPreferenceService preferenceService;

	@RenderMapping
	public String edit(RenderRequest request, Model model) {
		model.addAttribute("allowLabel",preferenceService.allowUserFeeds(request) ? DISALLOW:ALLOW);
		return "edit-global";
	}

	@ResourceMapping ("allowUserFeedsToggle")
	public void allowUserFeedsToggle(ResourceRequest request, ResourceResponse response) throws FeedPreferenceException, JsonIOException, IOException {					

		boolean currentlyAllowed = preferenceService.allowUserFeeds(request);
		Gson gson = new GsonBuilder().create();
		if (!currentlyAllowed) {			
			preferenceService.allowUserFeeds(request, true);
			gson.toJson(DISALLOW, response.getWriter());
		} else {
			preferenceService.allowUserFeeds(request, false);
			gson.toJson(ALLOW, response.getWriter());			
		}
		
		
	}
	
	@ExceptionHandler(Exception.class) 
	public void handleException(PortletResponse response) {
		PortalUtil.getHttpServletResponse(response).setStatus(500);
	}
}

	