package edu.pragmatic.java.advanced.rss.project.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DemoRss {

	public static void main(String[] args) {
		MyRssReader reader = new MyRssReader();
//		reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
//		reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
//		reader.removeFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");

		final Map<String, List<RssInfo>> allFeeds = reader.getAllFeeds();
		for (Entry<String, List<RssInfo>> entry : allFeeds.entrySet()) {
			System.out.printf("SORCE %s\n\n",entry.getKey());
			final List<RssInfo> value = entry.getValue();
			for (RssInfo rssInfo : value) {
				System.out.println(rssInfo.toString());
			}
		}
	}

}
