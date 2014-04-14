/**
 * Copyright (c) 2014 Solution Design Group All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.solutiondesign.rss.controller;

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
import com.solutiondesign.rss.exception.FeedPreferenceException;
import com.solutiondesign.rss.service.FeedPreferenceService;

@RequestMapping("EDIT")
public class RssEditController {

	private static final String DISALLOW = "true";
	private static final String ALLOW = "false";
	
	@Autowired
	FeedPreferenceService preferenceService;

	@RenderMapping
	public String edit(RenderRequest request, Model model) {
		model.addAttribute("allowLabel",preferenceService.allowUserFeeds(request) ? DISALLOW:ALLOW);
		model.addAttribute("showActivities",preferenceService.showActivities(request) ? DISALLOW:ALLOW);
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

	@ResourceMapping ("showActivitiesToggle")
	public void showActivitiesToggle(ResourceRequest request, ResourceResponse response) throws FeedPreferenceException, JsonIOException, IOException {
		boolean currentlyAllowed = preferenceService.showActivities(request);
		Gson gson = new GsonBuilder().create();
		
		if (!currentlyAllowed) {
			preferenceService.showActivities(request, true);
			gson.toJson(DISALLOW, response.getWriter());
		} else {
			preferenceService.showActivities(request, false);
			gson.toJson(ALLOW, response.getWriter());
		}
	}
	
	@ExceptionHandler(Exception.class) 
	public void handleException(PortletResponse response) {
		PortalUtil.getHttpServletResponse(response).setStatus(500);
	}
}

	