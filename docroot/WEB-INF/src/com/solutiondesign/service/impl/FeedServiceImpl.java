/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.solutiondesign.service.impl;

import java.util.Set;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.solutiondesign.model.Feed;
import com.solutiondesign.model.impl.FeedImpl;
import com.solutiondesign.service.base.FeedServiceBaseImpl;
import com.google.gson.Gson;

/**
 * The implementation of the feed remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.solutiondesign.service.FeedService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Bryan Smith
 * @see com.solutiondesign.service.base.FeedServiceBaseImpl
 * @see com.solutiondesign.service.FeedServiceUtil
 */
public class FeedServiceImpl extends FeedServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.solutiondesign.service.FeedServiceUtil} to access the feed remote service.
	 */
	
	public final Gson gson;
	
	public FeedServiceImpl() {
		gson = new Gson();
	}
	
	public Set<Feed> myFeeds() {
		return getFeedLocalService().myFeeds();
	}
	
	public Feed addFeedJson(String json) throws SystemException{
		FeedImpl feed = gson.fromJson(json, FeedImpl.class);

		return getFeedLocalService().addFeed(feed);
	}
	
	public Feed addFeed(String url, String scope) throws NoSuchUserException, SystemException {
		return getFeedLocalService().addFeed(url, scope);
	}
	
	public Feed updateFeed(long feedId, String url, String scope) throws SystemException, NoSuchUserException {
		return getFeedLocalService().updateFeed(feedId, url, scope);
	}
	
	public Feed deleteFeed(long feedId, String scope) throws PortalException, SystemException {
		return getFeedLocalService().deleteFeed(feedId, scope);
	}
}