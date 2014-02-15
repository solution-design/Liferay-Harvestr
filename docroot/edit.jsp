<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:actionURL var="actionURL"/>	


<form:form action="${actionURL}" method="post">
	<liferay-ui:search-container emptyResultsMessage="No RSS feeds" delta="1000">
		<liferay-ui:search-container-results>
			<%
			pageContext.setAttribute("results", request.getAttribute("feeds"));
			%>
		</liferay-ui:search-container-results>
		<liferay-ui:search-container-row
			className="com.solutiondesign.model.Feed"
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