;'use strict';
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

var SDG = SDG || {};

/*
 * Instantiate the RssPortlet
 */
AUI().use(
	'aui-tabview',
	'aui-datatable',
	'aui-datatype',
	'datatable-sort',
	'datatable-mutable',
	/*
	 * @param A: AlloyUI instantiated with aui-tabview, aui-datatable,
	 * 	aui-datatype, datatable-sort, and datatable-mutable
	 */
	function(A) {
		SDG.RssPortlet = $.extend(SDG.RssPortlet, {
			dataTable: null,
			feeds: [],
			feedItems: [],
			feedItemTemplate: A.one('#feed-item-template'),
			init: function () {
				A.one("html").delegate('click', this.onAddNewFeed, '.addNewBtn');
				A.one("#myDataTable").delegate('click', this.onDeleteFeed, '.removeFeed');
				A.one("html").delegate('click', this.onEditFeeds, '.editTab');
				
				if (this.feedItemTemplate){
					var source = this.feedItemTemplate._node.innerHTML;
					this.template = Handlebars.compile(source);
					 /* 
					  * Setup tab view
					  */
					this.tabView = new A.TabView({
						srcNode : '#myTab',
						type: 'pills'
					}).render();
					
					this.tabView.after('selectionChange', function (e) {
						if (e.newVal.getAttrs().label == "Feeds"){
							SDG.RssPortlet.getSocialActivities();
							SDG.RssPortlet.showFeedItems();
						}
					});
				}

				if (SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp') {
					SDG.RssPortlet.onEditFeeds();
					
					var allowUserFeedsSwitch = document.querySelector('#allowUserFeeds.js-switch');
					var showActivitiesSwitch = document.querySelector('#showActivities.js-switch');

					allowUserFeedsSwitch.onchange = this.onAllowUserFeeds;
					showActivitiesSwitch.onchange = this.onShowActivities;

					SDG.RssPortlet.allowUserFeedsSwitch = new Switchery(allowUserFeedsSwitch, {color: '#00aaff'});
					SDG.RssPortlet.showActivitiesSwitch = new Switchery(showActivitiesSwitch, {color: '#00aaff'});
				}
				else if (SDG.RssPortlet.PortletViewName == '/jsp/view.jsp'){
					if (SDG.RssPortlet.ShowActivities === "true") 
						SDG.RssPortlet.getSocialActivities();
					SDG.RssPortlet.showFeedItems();
				}
			},
			populateFeeds: function(callback) {
				/*
				 * Get the Feed URL's
				 */
				Liferay.Service('/RSS-portlet.feed/my-feeds',
					function(feeds) {
						/*
						 *   Return if user doesn't have any feeds setup
						 *  TODO: Handle this in a better way
						 */
						if (!_.isArray(feeds)) return;
						SDG.RssPortlet.feeds = feeds;

						if (callback && typeof(callback) === "function")
							callback.apply(feeds);
				});
			},
			populateFeedItems: function(callback) {
				if (SDG.RssPortlet.feedItemTemplate && SDG.RssPortlet.feeds) {
					_.each(SDG.RssPortlet.feeds,
						function(feed) {
							$.ajax({
								url : '//ajax.googleapis.com/ajax/services/feed/load?v=1.0&num='+ 3+ '&output=json&q='+ feed.url+ '&hl=en&callback=?',
								dataType : 'json',
								success : function(data) {
									/*
									 * Google can't get the RSS feed
									 */
									if (data.responseStatus == 400) return;
									
									/*
									 * Union new feed items with previous feed items and order by
									 * the publish date, descending
									 */ 
									SDG.RssPortlet.feedItems = _.sortBy(
										_.union(SDG.RssPortlet.feedItems, data.responseData.feed.entries),
										function(item) {return moment(item.publishedDate).format();}
									).reverse();
																		
									if (callback && typeof(callback) === "function")
										callback.apply();
								}
							});
					});
				}
			},
			showFeedItems: function() {
				SDG.RssPortlet.feedItems = [];
				$('#feedList').empty();
				SDG.RssPortlet.populateFeeds(function(){
					SDG.RssPortlet.populateFeedItems(function() {
						/*
						 * Use Handlebars template to render and append on the list
						 */
						_.each(SDG.RssPortlet.feedItems,
							function(item) {
								item.publishedDate = moment(item.publishedDate).fromNow();
								$('#feedList').append(SDG.RssPortlet.template(item));
						});				
					});
				});
			},
			getSocialActivities: function(){
				$.ajax({
					url: SDG.RssPortlet.UserGroupsActivitiesURL,
					dataType:'json',
					success:function(data){
						for (var i = 0; i < data.length; i++) {
							if (data[i] != null) {
								$('#feedList').prepend('<li>' + data[i]._title + '<hr /></li>');
							}
						}
					}
				});
			},
			onAllowUserFeeds: function (event) {
				event.preventDefault();
				var url = event.currentTarget.getAttribute("data-url");

				A.io.request(url,
					{
						method: 'GET',
						dataType: 'json',
						on: {
							success: function() {},
							failure: function(data) {
								alert("There was a problem serving your request");
							}
						}
					});
			},
			onShowActivities: function (event) {
				event.preventDefault();
				var url = event.currentTarget.getAttribute("data-url");

				A.io.request(url,
					{
						method: 'GET',
						dataType: 'json',
						on: {
							success: function() {},
							failure: function(data) {
								alert("There was a problem serving your request");
							}
						}
					});
			},
			onAddNewFeed: function (event) {
				var newFeed = A.one("#newFeed").val();
				if (!newFeed) {
					alert("Must enter text");
				}
				else {
					Liferay.Service('/RSS-portlet.feed/add-feed', 
						{
							url: newFeed,
							scope: SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp' ? 'global' : 'user'
						},
						function(obj) {
							SDG.RssPortlet.dataTable.addRow(
								{
									feedId: obj.feedId,
									url: obj.url,
								}
							);
							SDG.RssPortlet.dataTable.syncUI();
						},
						function(obj) {
							alert ('There was an error trying to add');
						}
					);
				}
			},
			onDeleteFeed: function (event) {
				event.preventDefault();
				var feedId=event.currentTarget.getAttribute('data-feedId');
				if (confirm ('Are you sure?')) {
					Liferay.Service('/RSS-portlet.feed/delete-feed',
						{
							feedId: feedId
						},
						function(obj) {
							var record = SDG.RssPortlet.dataTable.getRecord(event.currentTarget.get('id'));
							SDG.RssPortlet.dataTable.removeRow(record);
						},
						function(obj) {
							alert('There was an error trying to delete');
						});
				}
			},
			onEditFeeds: function() {
				if (!_.isObject(SDG.RssPortlet.dataTable)) {
					SDG.RssPortlet.populateFeeds(function() {
						var feeds = this;
						
						if (SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp')
							feeds = _.filter(feeds, function(feed){
								return feed.scope.toLowerCase() == 'global';
							});
						else
							feeds = _.filter(feeds, function(feed) {
								return feed.scope.toLowerCase() == 'user';
							});

						/*
						 * Setup data table
						 */
						SDG.RssPortlet.dataTable = new A.DataTable({
							columns : [{
								editor : new A.TextAreaCellEditor({
									validator : {
										rules : {
											url : {
												required : true
											}
										}
									}
								}),
								key : 'url',
								sortable : true,
								allowHTML: true,
								formatter: '<div class="urlCell">{value}<i class="fa icon-pencil fa-fw"></i></div>',
								label: ' ',
								width:'88%'
							},
							{
								key : 'feedId',
								label : ' ',
								className : 'edit-button',
								allowHTML : true,
								formatter : '<button class="btn removeFeed btn-danger" data-feedId="{value}"><i class="icon-trash"></i></button>',
								width : '12%'
							}],
							data : feeds,
							editEvent : 'click'
						});

						SDG.RssPortlet.dataTable.render('#myDataTable');

						/*
						 * On URL Edit, lookup the feed, and update the DB
						 */
						SDG.RssPortlet.dataTable.subscribe(
							'model:urlChange',
							function(e) {
								var prevUrlFeed = _.find(SDG.RssPortlet.feeds,
									function(feed) {
										return feed.url == e.prevVal;
								});
								/*
								 * TODO: Add better error handling
								 */
								if (_.isUndefined(prevUrlFeed))
									return;

								Liferay.Service(
									'/RSS-portlet.feed/update-feed', 
									{
										feedId : prevUrlFeed.feedId,
										url : e.newVal,
										scope : SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp' ? 'global' : 'user'
									},
									function (obj) {
										SDG.RssPortlet.dataTable.syncUI();
								});
						});
					});
				}
			}
	});
	
	SDG.RssPortlet.init();
});
