package edu.pragmatic.java.advanced.rss.project.model;

import java.util.List;

public class DemoRss {

	public static void main(String[] args) {
		MyRssReader reader = new MyRssReader();
		reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");

		final List<RssInfo> allFeeds = reader.getAllFeeds();
		for (RssInfo rssInfo : allFeeds) {
//			System.out.println(rssInfo.toString());
		}
	}

}
