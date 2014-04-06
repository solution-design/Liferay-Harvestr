<%
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
%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:actionURL var="actionURL"/>	

<%@ include file="/jsp/errata.jsp" %>

<form:form action="${actionURL}" method="post">
	<liferay-ui:search-container emptyResultsMessage="No RSS feeds" delta="1000">
		<liferay-ui:search-container-results>
			<%
			pageContext.setAttribute("results", request.getAttribute("feeds"));
			%>
		</liferay-ui:search-container-results>
		<liferay-ui:search-container-row
			className="com.solutiondesign.rss.model.Feed"
			modelVar="feed">			
			<liferay-ui:search-container-column-text name="Feed"> 
				${feed.url}			
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="">
				<input type="checkbox" name="feedsToRemove" value="${feed.feedId}"/>
			</liferay-ui:search-container-column-text>			
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
	<button type="submit">Remove</button>
</form:form>

<div id="grid"></div>