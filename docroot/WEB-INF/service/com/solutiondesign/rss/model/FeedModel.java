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

package com.solutiondesign.rss.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Feed service. Represents a row in the &quot;RSS_Feed&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.solutiondesign.rss.model.impl.FeedModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.solutiondesign.rss.model.impl.FeedImpl}.
 * </p>
 *
 * @author Bryan Smith
 * @see Feed
 * @see com.solutiondesign.rss.model.impl.FeedImpl
 * @see com.solutiondesign.rss.model.impl.FeedModelImpl
 * @generated
 */
public interface FeedModel extends BaseModel<Feed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a feed model instance should use the {@link Feed} interface instead.
	 */

	/**
	 * Returns the primary key of this feed.
	 *
	 * @return the primary key of this feed
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this feed.
	 *
	 * @param primaryKey the primary key of this feed
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the feed ID of this feed.
	 *
	 * @return the feed ID of this feed
	 */
	public long getFeedId();

	/**
	 * Sets the feed ID of this feed.
	 *
	 * @param feedId the feed ID of this feed
	 */
	public void setFeedId(long feedId);

	/**
	 * Returns the company ID of this feed.
	 *
	 * @return the company ID of this feed
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this feed.
	 *
	 * @param companyId the company ID of this feed
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this feed.
	 *
	 * @return the user ID of this feed
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this feed.
	 *
	 * @param userId the user ID of this feed
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this feed.
	 *
	 * @return the user uuid of this feed
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this feed.
	 *
	 * @param userUuid the user uuid of this feed
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this feed.
	 *
	 * @return the create date of this feed
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this feed.
	 *
	 * @param createDate the create date of this feed
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this feed.
	 *
	 * @return the modified date of this feed
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this feed.
	 *
	 * @param modifiedDate the modified date of this feed
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the url of this feed.
	 *
	 * @return the url of this feed
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this feed.
	 *
	 * @param url the url of this feed
	 */
	public void setUrl(String url);

	/**
	 * Returns the scope of this feed.
	 *
	 * @return the scope of this feed
	 */
	@AutoEscape
	public String getScope();

	/**
	 * Sets the scope of this feed.
	 *
	 * @param scope the scope of this feed
	 */
	public void setScope(String scope);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Feed feed);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Feed> toCacheModel();

	@Override
	public Feed toEscapedModel();

	@Override
	public Feed toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}