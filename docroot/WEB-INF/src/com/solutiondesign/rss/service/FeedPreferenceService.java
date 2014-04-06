package com.solutiondesign.rss.service;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;

import com.solutiondesign.rss.exception.FeedPreferenceException;

@SuppressWarnings("unused")
public interface FeedPreferenceService {
	public Boolean allowUserFeeds(PortletRequest request);
	public void allowUserFeeds(PortletRequest request, boolean val) throws FeedPreferenceException;
}
