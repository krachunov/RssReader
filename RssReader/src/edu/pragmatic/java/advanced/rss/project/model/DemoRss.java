/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.io.IOException;


import edu.pragmatic.java.advanced.rss.project.UI.StartDisplay;
import javafx.application.Application;

public class DemoRss {

	public static void main(String[] args) throws IOException {
		// RssOption reader = new MyRssReader();
		// Scanner sc = new Scanner(System.in);
		// String inputUri = sc.nextLine();
		// if (!inputUri.equals("")) {
		// reader.addFeedSorce(inputUri);
		//
		// }

		// reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
		// reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		// reader.removeFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		// reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");

		// Test displayAllSources
		// final List<String> displayAllSources = reader.displayAllSources();
		// for (String string : displayAllSources) {
		// System.out.println(string);
		// }
		// RssOption reader = new MyRssReader();
		// reader.addFeedSorce("https://sports.yahoo.com/top/rss.xml");
		// reader.addFeedSorce("https://sports.yahoo.com/golf/blog/devil_ball_golf/rss.xml");
		// reader.addFeedSorce("http://rss.cnn.com/rss/edition.rss");

		//
		// Test Display All News
		// final List<String> displayAllSources = reader.displayAllSources();
		// for (String string : displayAllSources) {
		// System.out.println(string);
		// }
		// final List<RssInfo> displayAllnews = reader.displayAllNews();
		// for (RssInfo rssInfo : displayAllnews) {
		// System.out.println(rssInfo.getTitle());
		// }
		// reader.serialize();
		Application.launch(StartDisplay.class, args);
	}

}
