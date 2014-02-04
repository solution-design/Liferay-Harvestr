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

YUI().use('aui-modal', function(Y) {
/*	var modal = new Y.Modal({
		bodyContent : '<div id="myDataTable"></div>',
		centered : true,
		destroyOnHide : false,
		headerContent : '<h3>RSS Configuration</h3>',
		height : 400,
		modal : true,
		render : '#modal',
		visible : true,
		width : 600
	}).render();*/

/*	modal.addToolbar([ {
		label : 'Cancel',
		on : {
			click : function() {
				modal.hide();
			}
		}
	}, {
		label : 'Save',
		on : {
			click : function() {
				//alert('Just an example, there will be no printing here.');
			}
		}
	} ]);*/

/*	Y.one('#showModal').on('click', function() {
		modal.show();
	});*/
});

/*$(document).ready(function(){
	jQuery("#grid").jqGrid({
	   	url:'server.php?q=2',
		datatype: "json",
	   	colNames:['ID','URL', 'Stories', 'Enabled'],
	   	colModel:[
	   		{name:'id',index:'id', width:55},
	   		{name:'invdate',index:'invdate', width:90},
	   		{name:'name',index:'name asc, invdate', width:100},
	   		{name:'amount',index:'amount', width:80, align:"right"}		
	   	],
	   	rowNum:10,
	   	rowList:[10,20,30],
	   	//pager: '#pager2',
	   	sortname: 'id',
	    viewrecords: true,
	    sortorder: "desc",
	    caption:"JSON Example"
	});
});*/

/*YUI().use(
		'aui-datatable',
		'aui-datatype',
		'datatable-sort',
		function(Y) {
			Liferay.Service('/RSS-portlet.feed/my-feeds', function(feeds) {
				var nestedCols = [ {
					editor : new Y.TextAreaCellEditor({
						validator : {
							rules : {
								value : {
									required : true
								}
							}
						}
					}),
					key : 'url',
					sortable : true
				}, {
					editor : new Y.TextCellEditor({
						inputFormatter : Y.DataType.String.evaluate,
						validator : {
							rules : {
								value : {
									number : true,
									required : true
								}
							}
						}
					}),
					key : 'feedId'
				}, {
					editor : new Y.RadioCellEditor({
						editable : true,
						options : {
							yes : 'Yes',
							no : 'No',
							maybe : 'Maybe'
						}
					}),
					key : 'active'
				} ];

				var dataTable = new Y.DataTable({
					columns : nestedCols,
					data : feeds,
					editEvent : 'dblclick',
					plugins : [ {
						cfg : {
							highlightRange : false
						},
						fn : Y.Plugin.DataTableHighlight
					} ]
				}).render('#myDataTable');

				dataTable.get('boundingBox').unselectable();
			});
			
			
		});*/