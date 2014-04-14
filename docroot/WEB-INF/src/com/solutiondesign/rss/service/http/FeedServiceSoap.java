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

package com.solutiondesign.rss.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.solutiondesign.rss.service.FeedServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.solutiondesign.rss.service.FeedServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.solutiondesign.rss.model.FeedSoap}.
 * If the method in the service utility returns a
 * {@link com.solutiondesign.rss.model.Feed}, that is translated to a
 * {@link com.solutiondesign.rss.model.FeedSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Bryan Smith
 * @see FeedServiceHttp
 * @see com.solutiondesign.rss.model.FeedSoap
 * @see com.solutiondesign.rss.service.FeedServiceUtil
 * @generated
 */
public class FeedServiceSoap {
	public static java.util.Set<com.solutiondesign.rss.model.Feed> myFeeds()
		throws RemoteException {
		try {
			java.util.Set<com.solutiondesign.rss.model.Feed> returnValue = FeedServiceUtil.myFeeds();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.solutiondesign.rss.model.FeedSoap addFeed(
		java.lang.String url, java.lang.String scope) throws RemoteException {
		try {
			com.solutiondesign.rss.model.Feed returnValue = FeedServiceUtil.addFeed(url,
					scope);

			return com.solutiondesign.rss.model.FeedSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.solutiondesign.rss.model.FeedSoap updateFeed(
		long feedId, java.lang.String url, java.lang.String scope)
		throws RemoteException {
		try {
			com.solutiondesign.rss.model.Feed returnValue = FeedServiceUtil.updateFeed(feedId,
					url, scope);

			return com.solutiondesign.rss.model.FeedSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.solutiondesign.rss.model.FeedSoap deleteFeed(long feedId)
		throws RemoteException {
		try {
			com.solutiondesign.rss.model.Feed returnValue = FeedServiceUtil.deleteFeed(feedId);

			return com.solutiondesign.rss.model.FeedSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FeedServiceSoap.class);
}