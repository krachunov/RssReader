package edu.pragmatic.java.advanced.rss.project.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class MyRssReader implements RssOption {

	private Map<String, List<RssInfo>> allFeeds;

	public MyRssReader() {
		this.allFeeds = new TreeMap<String, List<RssInfo>>();
	}

	public Map<String, List<RssInfo>> getAllFeeds() {
		return allFeeds;
	}

	public void setAllFeeds(Map<String, List<RssInfo>> allFeeds) {
		this.allFeeds = allFeeds;
	}

	@Override
	public void addFeedSorce(String url) {
		URL feedSource = null;
		try {
			feedSource = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(feedSource));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final List<?> entries = feed.getEntries();
		List<RssInfo> allFeeds = new ArrayList<>();
		for (Object object : entries) {
			final SyndEntryImpl syndEntryImpl = (SyndEntryImpl) object;
			RssInfo currentFeed = new RssInfo();

			currentFeed.setTitle(syndEntryImpl.getTitle());
			currentFeed.setAuthor(syndEntryImpl.getAuthor());
			currentFeed.setUri(syndEntryImpl.getUri());
			currentFeed.setDescription(((SyndEntryImpl) object)
					.getDescription().getValue());
			currentFeed.setPubDate(syndEntryImpl.getPublishedDate());
			allFeeds.add(currentFeed);
			Collections.sort(allFeeds);
		}
		getAllFeeds().put(url, allFeeds);

	}

	/**
	 * Removes the selected source
	 * 
	 * @return - true if removed or false if it is not removed or does not exist
	 */
	@Override
	public boolean removeFeedSorce(String url) {
		final List<RssInfo> remove = this.allFeeds.remove(url);
		return remove != null ? true : false;
	}

	@Override
	public List<RssInfo> displayAllSorces() {
		return null;

	}

	@Override
	public List<RssInfo> displayAllNews() {
		List<RssInfo> allNews = new ArrayList<>();
		for (Entry<String, List<RssInfo>> entry : getAllFeeds().entrySet()) {
			allNews.addAll(entry.getValue());
		}
		Collections.sort(allNews);
		return allNews;

	}

	@Override
	public List<RssInfo> displayingOnlyUnreadNews() {
		List<RssInfo> allUnreadNews = new ArrayList<RssInfo>();
		for (Entry<String, List<RssInfo>> entry : getAllFeeds().entrySet()) {
			final List<RssInfo> newsFromCurrentSource = entry.getValue();
			for (RssInfo rssInfo : newsFromCurrentSource) {
				if (!rssInfo.isVisited()) {
					allUnreadNews.add(rssInfo);
				}
			}
		}
		Collections.sort(allUnreadNews);
		return allUnreadNews;
	}
}
