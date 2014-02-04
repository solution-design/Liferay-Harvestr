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

package com.solutiondesign.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.solutiondesign.model.Feed;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Feed in entity cache.
 *
 * @author Bryan Smith
 * @see Feed
 * @generated
 */
public class FeedCacheModel implements CacheModel<Feed>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{feedId=");
		sb.append(feedId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", url=");
		sb.append(url);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Feed toEntityModel() {
		FeedImpl feedImpl = new FeedImpl();

		feedImpl.setFeedId(feedId);
		feedImpl.setCompanyId(companyId);
		feedImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			feedImpl.setCreateDate(null);
		}
		else {
			feedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			feedImpl.setModifiedDate(null);
		}
		else {
			feedImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (url == null) {
			feedImpl.setUrl(StringPool.BLANK);
		}
		else {
			feedImpl.setUrl(url);
		}

		feedImpl.resetOriginalValues();

		return feedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		feedId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		url = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(feedId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}
	}

	public long feedId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String url;
}