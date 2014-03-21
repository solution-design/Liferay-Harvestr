;'use strict';

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
				A.one("#myDataTable").delegate('click', this.onDelete, '.removeFeed');
				A.one("html").delegate('click', this.onAllowUserFeeds, '.allowUserFeedsBtn');
				A.one("html").delegate('click', this.onAddNewFeed, '.addNewBtn');
				A.one("html").delegate('click', this.editFeeds, '.editTab');
				
				if (this.feedItemTemplate){
					source = this.feedItemTemplate.html();
					template = Handlebars.compile(source);
					 /* 
					  * Setup tab view
					  */
					new A.TabView({
						srcNode : '#myTab',
						type: 'pills'
					}).render();
				}
				
				if (SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp')
					SDG.RssPortlet.editFeeds();
				else if (SDG.RssPortlet.PortletViewName == '/jsp/view.jsp')
					SDG.RssPortlet.showFeedItems();
				
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
			onAllowUserFeeds: function (event) {
				event.preventDefault();
				var url = event.currentTarget.getAttribute("data-url");
				var button=A.one('.allowUserFeedsBtn');
				var originalLabel = button.html();
				button.html("Wait...");
				button.set('disabled',true);
				A.io.request(url, {
					   method: 'GET',
					   dataType: 'json',
					   on: { 
						   success: function() { 						 
							   button.html(this.get('responseData'));
							   button.set('disabled',false);
						   },
						   failure: function() { 
							   alert("There was a problem serving your request");
							   button.set('disabled',false);
							   button.html(originalLabel);
						   }
					   }
					});
			},
			onDelete: function (event) {
				event.preventDefault();			
				var feedId=event.currentTarget.getAttribute('data-feedId');
				if (confirm ('Are you sure?')) {
					Liferay.Service(
							  '/RSS-portlet.feed/delete-feed',
							  {
							    feedId: feedId
							  },
							  function(obj) {
							    var record = SDG.RssPortlet.dataTable.getRecord(event.currentTarget.get('id'));
							    SDG.RssPortlet.dataTable.removeRow(record);
							  },
							  function(obj) {
								  alert('There was an error trying to delete');
							  }
							);
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
								url : 'http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num='+ 3+ '&output=json&q='+ feed.url+ '&hl=en&callback=?',
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
				SDG.RssPortlet.populateFeeds(function(){
					SDG.RssPortlet.populateFeedItems(function() {
						/*
						 * Empty any previous items because we have a superset of that data
						 */
						$('#feedList').empty();
		
						/*
						 * Use Handlebars template to render and append on the list
						 */
						_.each(SDG.RssPortlet.feedItems,
							function(item) {
								item.publishedDate = moment(item.publishedDate).fromNow();
								$('#feedList').append(template(item));
						});				
					});
				});
			},
			editFeeds: function() {
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
		                        key:'feedId',
		                        label:' ',
		                        className:'edit-button',
		                        allowHTML: true,
		                        formatter: '<button class="btn removeFeed btn-danger" data-feedId="{value}"><i class="icon-trash"></i></button>',
		                        width: '12%'
		                    }],
							data : feeds,
							editEvent : 'click'
						});
						
						SDG.RssPortlet.dataTable.render('#myDataTable');						
	
						/*
						 * On URL Edit, lookup the feed, and update the DB
						 */
						SDG.RssPortlet.dataTable.subscribe('model:urlChange', function(e){
							var prevUrlFeed = _.find(SDG.RssPortlet.feeds, function(feed){
								return feed.url == e.prevVal;
							});
		
							/*
							 * TODO: Add better error handling
							 */
							if (_.isUndefined(prevUrlFeed)) return;
						
							Liferay.Service('/RSS-portlet.feed/update-feed',
								{  	
									feedId: prevUrlFeed.feedId,
							    	url: e.newVal,
							    	scope: SDG.RssPortlet.PortletViewName == '/jsp/edit-global.jsp' ? 'global' : 'user'
						    	},
						    	function(obj) {
						    		console.log(obj);
						    		SDG.RssPortlet.dataTable.syncUI();
						    	}
							);
						});
					});
				}
			}
		});
		
		SDG.RssPortlet.init();
});