Liferay.Service.register("Liferay.Service.Feed", "com.solutiondesign.rss.feed.service", "feed-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Feed, "Feed",
	{
		myFeeds: true,
		makeFeed: true
	}
);