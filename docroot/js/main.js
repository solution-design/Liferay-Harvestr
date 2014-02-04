AUI().ready(
	function() {
		var items = [];
		var source   = $("#feed-item-template").html();
		var template = Handlebars.compile(source);
		
		Liferay.Service('/RSS-portlet.feed/my-feeds', function(feeds) {
			if (!_.isArray(feeds)) return;
	
			_.each(feeds, function(feed) {
		        $.ajax({
		            url: "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=" + 3 + "&output=json&q=" + feed.url + "&hl=en&callback=?",
		            dataType: "json",
		            success: function(data) {
		            	if (data.responseStatus == 400) return;
						items = _.sortBy(
								_.union(items, data.responseData.feed.entries),
								function(item) {
									return moment(item.publishedDate).format();
								}).reverse();
						
						$('#feeds').empty();
						
						_.each(items, function(item) {
							item.publishedDate = moment(item.publishedDate).fromNow();
							$('#feeds').append(template(item));
							
						});
					}
		        });
			});
		});
	});