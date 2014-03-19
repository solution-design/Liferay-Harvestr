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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%@ include file="/jsp/errata.jsp" %>
<%@ include file="/jsp/feed-item-template.jsp" %>

<div id="myTab">
  <div class="tab-content">
    <div id="tab-1" class="tab-pane">
    	<ul id="feedList" style="list-style-type: none;"></ul>
    </div>
		<div id="tab-2">
			<div class="input-group margin-bottom-sm">
				<input id="newFeed" type="text" value="">
				<button class="addNewBtn">Add</button>
				<span class="input-group-addon"><i class="fa fa-pencil fa-fw"></i></span>
			</div>
			<div id="myDataTable"></div>
		</div>
	</div>
  <c:if test="${!allowUserFeed}">
  	<c:set var="hiddenClass" value="hidden"/>
  </c:if>
  <ul class="nav nav-tabs ${hiddenClass}">
    <li class="active feedsTab"><a href="#tab-1">Feeds</a></li>
    <li class="editTab"><a href="#tab-2">Edit</a></li>
  </ul>
</div>