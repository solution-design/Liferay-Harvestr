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

package com.solutiondesign.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FeedService}.
 *
 * @author Bryan Smith
 * @see FeedService
 * @generated
 */
public class FeedServiceWrapper implements FeedService,
	ServiceWrapper<FeedService> {
	public FeedServiceWrapper(FeedService feedService) {
		_feedService = feedService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _feedService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_feedService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _feedService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.Set<com.solutiondesign.model.Feed> myFeeds() {
		return _feedService.myFeeds();
	}

	@Override
	public com.solutiondesign.model.Feed addFeedJson(java.lang.String json)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedService.addFeedJson(json);
	}

	@Override
	public com.solutiondesign.model.Feed addFeed(java.lang.String url,
		java.lang.String scope)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedService.addFeed(url, scope);
	}

	@Override
	public com.solutiondesign.model.Feed updateFeed(long feedId,
		java.lang.String url, java.lang.String scope)
		throws com.liferay.portal.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedService.updateFeed(feedId, url, scope);
	}

	@Override
	public com.solutiondesign.model.Feed deleteFeed(long feedId,
		java.lang.String scope)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedService.deleteFeed(feedId, scope);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public FeedService getWrappedFeedService() {
		return _feedService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedFeedService(FeedService feedService) {
		_feedService = feedService;
	}

	@Override
	public FeedService getWrappedService() {
		return _feedService;
	}

	@Override
	public void setWrappedService(FeedService feedService) {
		_feedService = feedService;
	}

	private FeedService _feedService;
}