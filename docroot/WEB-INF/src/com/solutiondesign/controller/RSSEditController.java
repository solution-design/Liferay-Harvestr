package com.solutiondesign.controller;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ValidatorException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

@RequestMapping("EDIT")
public class RSSEditController {

	private static final String DISALLOW = "Disallow user feeds";
	private static final String ALLOW = "Allow user feeds";

	@RenderMapping
	public String edit(RenderRequest request, Model model) {
		PortletPreferences prefs = request.getPreferences();
		String currentValue = prefs.getValue("allowUserFeeds","false");	
		Boolean allowFeeds = Boolean.valueOf(currentValue);
		model.addAttribute("allowLabel",allowFeeds ? ALLOW:DISALLOW);
		return "edit-global";
	}
	
	@ResourceMapping ("allowUserFeedsToggle")
	public void allowUserFeedsToggle(ResourceRequest request, ResourceResponse response) throws ReadOnlyException, PortalException, SystemException, ValidatorException, IOException {					
		PortletPreferences prefs = request.getPreferences();
		String currentValue = prefs.getValue("allowUserFeeds","false");		
		boolean currentlyAllowed = !currentValue.equals("false");
		Gson gson = new GsonBuilder().create();
		if (!currentlyAllowed) {
			prefs.setValue("allowUserFeeds", "true");
			gson.toJson(ALLOW, response.getWriter());
		} else {
			prefs.setValue("allowUserFeeds", "false");
			gson.toJson(DISALLOW, response.getWriter());			
		}
		prefs.store();
		
	}
	
	@ExceptionHandler(Exception.class) 
	public void handleException(PortletResponse response) {
		PortalUtil.getHttpServletResponse(response).setStatus(500);
	}
}

	