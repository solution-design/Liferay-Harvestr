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
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.solutiondesign.rss.service.FeedPreferenceService;
import com.solutiondesign.rss.service.impl.FeedLocalServiceImpl;

@RequestMapping("VIEW")
public class RssFeedController {
	@Autowired 
	FeedPreferenceService preferenceService;

	@Autowired
	FeedLocalServiceImpl feedLocalService;
	
	
	@RenderMapping
	public String render(RenderRequest request, Model model) {
		model.addAttribute("allowUserFeed",preferenceService.allowUserFeeds(request));
		return "view";
	}
	
	@ResourceMapping("getUserGroupsActivities")
	public void getUserGroupsActivities(ResourceRequest request, ResourceResponse response) throws SystemException, JsonIOException, IOException, PortalException {
		ThemeDisplay themeDisplay = 
			(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		Set<SocialActivityFeedEntry> activities = feedLocalService.getUserGroupsActivities(themeDisplay);
		
		Gson gson = new GsonBuilder().create();
		
		gson.toJson(activities, response.getWriter());
	}
}