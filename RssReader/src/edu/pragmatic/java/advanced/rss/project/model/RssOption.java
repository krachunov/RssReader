package edu.pragmatic.java.advanced.rss.project.model;

public interface RssOption  {
	void addFeedSorce(String url);
	void removeFeedSorce();
	void displayAllSorces();
	void displayAllNews();
	void displayingOnlyUnreadNews();
}
