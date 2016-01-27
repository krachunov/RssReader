/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DemoRss {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String inputUri = sc.nextLine();
		RssOption reader = new MyRssReader();
		if (!inputUri.equals("")) {
			reader.addFeedSorce(inputUri);

		}

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
			System.out.println(rssInfo.getTitle());
		}
		reader.serialize();
	}

}
