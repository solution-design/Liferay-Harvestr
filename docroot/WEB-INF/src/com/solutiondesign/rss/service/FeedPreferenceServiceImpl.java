package com.solutiondesign.rss.service;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Service;

import com.solutiondesign.rss.exception.FeedPreferenceException;

@Service
public class FeedPreferenceServiceImpl implements FeedPreferenceService {

	@Override
	public Boolean allowUserFeeds(PortletRequest request) {
		PortletPreferences prefs = request.getPreferences();
		String currentValue = prefs.getValue("allowUserFeeds","false");	
		Boolean allowFeeds = Boolean.valueOf(currentValue);
		return allowFeeds;
	}

	@Override
	public void allowUserFeeds(PortletRequest request, boolean val)
			throws FeedPreferenceException {
		try {
			PortletPreferences prefs = request.getPreferences();
			prefs.setValue("allowUserFeeds", String.valueOf(val));
			prefs.store();
		} catch (ReadOnlyException | ValidatorException | IOException e) {
			throw new FeedPreferenceException(e);
		}

	}

}
