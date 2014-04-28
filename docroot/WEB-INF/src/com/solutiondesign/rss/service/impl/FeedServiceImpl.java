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

import java.util.Set;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.solutiondesign.rss.model.Feed;
import com.solutiondesign.rss.service.base.FeedServiceBaseImpl;

/**
 * The implementation of the feed remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.solutiondesign.rss.service.FeedService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Bryan Smith
 * @see com.solutiondesign.rss.service.base.FeedServiceBaseImpl
 * @see com.solutiondesign.rss.service.FeedServiceUtil
 */
public class FeedServiceImpl extends FeedServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.solutiondesign.rss.service.FeedServiceUtil} to access the feed remote service.
	 */
	
	public Set<Feed> myFeeds() {
		return getFeedLocalService().myFeeds();
	}
	
	public Feed addFeed(String url, String scope) throws NoSuchUserException, SystemException {
		return getFeedLocalService().addFeed(url, scope);
	}
	
	public Feed updateFeed(long feedId, String url, String scope) throws SystemException, NoSuchUserException {
		return getFeedLocalService().updateFeed(feedId, url, scope);
	}
	
	public Feed deleteFeed(long feedId) throws PortalException, SystemException {
		return getFeedLocalService().deleteFeed(feedId);
	}
}