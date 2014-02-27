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


<portlet:resourceURL var="resourceURL" id="allowUserFeedsToggle"></portlet:resourceURL>
<div class="input-group margin-bottom-sm">
	<button class="allowUserFeedsBtn" data-url="${resourceURL}">${allowLabel}</button>
  <span class="input-group-addon"><i class="fa fa-pencil fa-fw"></i></span>
  <input class="form-control" type="text" value="Email address">
	
</div>
 		<div id="myDataTable"></div>
</div>