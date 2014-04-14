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
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<portlet:defineObjects />

<%@ include file="/jsp/errata.jsp" %>

<portlet:resourceURL var="allowUserFeedsURL" id="allowUserFeedsToggle" />
<portlet:resourceURL var="showActivitiesURL" id="showActivitiesToggle" />

<div class="input-group margin-bottom-sm">  
  <input id="newFeed" type="text" value=""><button class="addNewBtn">Add</button>
  <span class="input-group-addon"><i class="fa fa-pencil fa-fw"></i></span>
</div>

<div id="myDataTable"></div>

<c:if test="${allowLabel}">
	<c:set var="allowUserFeeds" value="checked"/>
</c:if>

<c:if test="${showActivities}">
	<c:set var="showActivities" value="checked"/>
</c:if>

Allow user feeds <input id="allowUserFeeds" type="checkbox" class="js-switch" data-url="${allowUserFeedsURL}" ${allowUserFeeds} />
<br/>
Show Activities <input id="showActivities" type="checkbox" class="js-switch" data-url="${showActivitiesURL}" ${showActivities} />