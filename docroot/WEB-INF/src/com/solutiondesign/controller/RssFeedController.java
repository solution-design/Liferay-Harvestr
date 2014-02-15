package com.solutiondesign.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.solutiondesign.service.FeedService;

@RequestMapping("VIEW")

public class RssFeedController {

	@Autowired 
	FeedService feedService;
	
	@RenderMapping
	public String render() {
		return "view";
	}
	
	@RenderMapping (params="view=edit") 
	public String handleEdit (Model model) {
		model.addAttribute("feeds",feedService.myFeeds());
		return "edit";
	}



    @ActionMapping ("delete")
    public void deleteFeed(int id) {

    }


}
