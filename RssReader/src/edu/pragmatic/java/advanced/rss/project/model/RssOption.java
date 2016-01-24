/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.util.List;
import java.util.Map;

public interface RssOption {
	Map<String, List<RssInfo>> getAllFeeds();

	void addFeedSorce(String url);

	boolean removeFeedSorce(String url);

	List<String> displayAllSources();

	List<RssInfo> displayAllNews();

	List<RssInfo> displayingOnlyUnreadNews();

}
