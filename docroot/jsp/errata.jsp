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
<portlet:resourceURL var="activitiesURL" id="getUserGroupsActivities" />
<script language="javascript">
var SDG = SDG || {};
SDG.RssPortlet || (SDG.RssPortlet = {});
SDG.RssPortlet.PortletNamespace = "<portlet:namespace />";
SDG.RssPortlet.PortletViewName = "${requestScope['javax.servlet.include.servlet_path']}";
SDG.RssPortlet.ActivitiesURL = "${activitiesURL}";
</script>