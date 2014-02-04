Liferay.Service.register("Liferay.Service.Feed", "com.solutiondesign.feed.service", "feed-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Feed, "Feed",
	{
		myFeeds: true,
		makeFeed: true
	}
);