package edu.pragmatic.java.advanced.rss.project.model;


import java.util.List;
import java.util.Map;

import edu.pragmatic.java.advanced.rss.project.model.RssInfo;

public interface RssOption {
	Map<String, List<RssInfo>> getAllSources();

	void addFeedSorce(String url);

	boolean removeFeedSorce(String url);

	List<String> displayAllSources();

	List<RssInfo> displayAllNews();

	List<RssInfo> displayingOnlyUnreadNews();

	void serialize();

}
