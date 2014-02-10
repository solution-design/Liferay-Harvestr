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

<portlet:defineObjects />

<script id="feed-item-template" type="text/x-handlebars-template">
<li>
	<div>
		<div>
			<div class="feed-title" style="display: inline;"><a href="{{link}}" target="_new">{{title}}</a></div>
			{{#if author}}
			<div class="itemAuthor" style="display: inline;"> by {{author}}, </div>
			{{/if}}
			<div class="feed-published-date feed-date" style="display: inline;">{{publishedDate}}</div>
		</div>
		<div class="itemContent" style="text-indent: 1em;">{{{content}}}</div>
		<div class="separator">
	</div>
</li>
</script>

<ul id="feeds" style="list-style-type: none;"></ul>

<portlet:renderURL var="editURL">
     <portlet:param name="view" value="edit" />
</portlet:renderURL>

<p><a href="${editURL}">Edit Feeds</a></p>