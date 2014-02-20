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

<div class="input-group margin-bottom-sm">
  <span class="input-group-addon"><i class="fa fa-pencil fa-fw"></i></span>
  <input class="form-control" type="text" value="Email address">
</div>

<div id="myTab">
  <div class="tab-content">
    <div id="tab-1" class="tab-pane">
    	<ul id="feedList" style="list-style-type: none;"></ul>
    </div>
    <div id="tab-2">
   		<div id="myDataTable"></div>
		<button class="btn" value="Add"><i class="icon-plus"> Add</i></button>
    </div>
  </div>
  <ul class="nav nav-tabs">
    <li><a href="#tab-1">Feeds</a></li>
    <li class="active"><a href="#tab-2">Edit</a></li>
  </ul>
</div>