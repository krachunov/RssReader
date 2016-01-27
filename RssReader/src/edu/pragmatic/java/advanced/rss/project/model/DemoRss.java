/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class DemoRss {

	public static void main(String[] args) {
		// MyRssReader reader = new MyRssReader();
		RssOption reader = new MyRssReader();
		reader.addFeedSorce("http://www.nerds2nerds.com/?feed=rss2");
		// reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
		// reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		// reader.removeFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		// reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");

		// Test displayAllSources
		// final List<String> displayAllSources = reader.displayAllSources();
		// for (String string : displayAllSources) {
		// System.out.println(string);
		// }
		// Test Display All News
		final List<RssInfo> displayAllnews = reader.displayAllNews();
		for (RssInfo rssInfo : displayAllnews) {
			System.out.println(rssInfo.getMedia());
		}
	}

}
