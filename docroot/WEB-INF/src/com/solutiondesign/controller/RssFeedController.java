package com.solutiondesign.controller;

import javax.portlet.RenderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.solutiondesign.service.FeedPreferenceService;

@RequestMapping("VIEW")
public class RssFeedController {
	@Autowired 
	FeedPreferenceService preferenceService;

	@RenderMapping
	public String render(RenderRequest request, Model model) {
		model.addAttribute("allowUserFeed",preferenceService.allowUserFeeds(request));
		return "view";
	}
		
}