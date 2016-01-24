package edu.pragmatic.java.advanced.rss.project.model;

import java.util.List;

public interface RssOption {
	void addFeedSorce(String url);

	boolean removeFeedSorce(String url);

	List<RssInfo> displayAllSorces();

	List<RssInfo> displayAllNews();

	List<RssInfo> displayingOnlyUnreadNews();
}
