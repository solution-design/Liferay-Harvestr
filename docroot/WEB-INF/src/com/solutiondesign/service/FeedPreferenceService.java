package com.solutiondesign.service;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;

import com.solutiondesign.exception.FeedPreferenceException;

public interface FeedPreferenceService {
	public Boolean allowUserFeeds(PortletRequest request);
	public void allowUserFeeds(PortletRequest request, boolean val) throws FeedPreferenceException;
}
