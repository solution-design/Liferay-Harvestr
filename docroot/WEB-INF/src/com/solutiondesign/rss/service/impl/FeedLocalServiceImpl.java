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

package com.solutiondesign.rss.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.solutiondesign.rss.model.Feed;
import com.solutiondesign.rss.service.base.FeedLocalServiceBaseImpl;

/**
 * The implementation of the feed local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.solutiondesign.rss.service.FeedLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Bryan Smith
 * @see com.solutiondesign.rss.service.base.FeedLocalServiceBaseImpl
 * @see com.solutiondesign.rss.service.FeedLocalServiceUtil
 */
public class FeedLocalServiceImpl extends FeedLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.solutiondesign.rss.service.FeedLocalServiceUtil} to access the feed local service.
	 */
	private static final Logger LOG = Logger.getLogger(FeedServiceImpl.class);

	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	private com.liferay.portal.service.UserLocalService userLocalService;
	
	public Set<SocialActivityFeedEntry> getUserGroupsActivities(ThemeDisplay themeDisplay) throws SystemException, PortalException {
		long userId = PrincipalThreadLocal.getUserId();

		List<SocialActivity> activities = SocialActivityLocalServiceUtil.getUserGroupsActivities(userId, 0, 3); 

		Set<SocialActivityFeedEntry> entries = new HashSet<SocialActivityFeedEntry>();
		for (SocialActivity activity : activities) {
			/*
			 * Using deprecated function because the other interpret() methods do not work
			 */
			SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, themeDisplay); 
			entries.add(entry);
		}

		return entries;
	}
	
	@SuppressWarnings("finally")
	public Set<Feed> myFeeds() {
		long userId = PrincipalThreadLocal.getUserId();
		Set<Feed> Feeds = new HashSet<Feed>();
		
		try {
			Feeds.addAll(feedPersistence.findByUserId(userId));
			Feeds.addAll(feedPersistence.findByScope("global"));
		} catch (SystemException e) {
			e.printStackTrace();
		}
		finally {
			return Feeds;
		}
	}
	
	public Feed addFeed(String url, String scope) throws SystemException,
			NoSuchUserException {
		long feedId = counterLocalService.increment(Feed.class.getName());
		long userId = PrincipalThreadLocal.getUserId();

		Feed feed = feedPersistence.create(feedId);
		User user = userPersistence.findByPrimaryKey(userId);

		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		Date now = dateCal.getTime();

		feed.setCompanyId(user.getCompanyId());
		feed.setUserId(user.getUserId());
		feed.setCreateDate(now);
		feed.setModifiedDate(now);
		feed.setUrl(url);
		feed.setScope(scope);

		return super.addFeed(feed);
	}

	public Feed updateFeed(long feedId, String url, String scope)
			throws NoSuchUserException, SystemException {
		long userId = PrincipalThreadLocal.getUserId();
		User user = userPersistence.findByPrimaryKey(userId);

		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		Date now = dateCal.getTime();

		Feed feed = feedPersistence.fetchByPrimaryKey(feedId);

		feed.setUrl(url);
		feed.setScope(scope);
		feed.setModifiedDate(now);

		return super.updateFeed(feed);
	}

	public Feed deleteFeed(long feedId) throws SystemException {
		Feed feed = feedPersistence.fetchByPrimaryKey(feedId);

		return super.deleteFeed(feed);
	}
}