package edu.pragmatic.java.advanced.rss.project.model;


import java.util.List;
import java.util.Map;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;

import edu.pragmatic.java.advanced.rss.project.model.RssInfo;

public interface RssOption {
	Map<String, List<RssInfo>> getAllSources();

	void addFeedSorce(String url);
	
	public SyndFeed createFeed(String url);

	boolean removeFeedSorce(String url);

	public RssInfo createRssInfoObject(SyndFeed feed,
			SyndEntryImpl syndEntryImpl);
	
	List<String> displayAllSources();

	List<RssInfo> displayAllNews();

	List<RssInfo> displayingOnlyUnreadNews();

	void serialize();

}
