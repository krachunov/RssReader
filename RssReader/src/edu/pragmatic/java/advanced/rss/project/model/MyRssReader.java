/**
 * @author Krachunov
 */
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

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class MyRssReader implements RssOption {

	/**
	 * @key - URL
	 * @value - List<RssInfo>
	 */
	private Map<String, List<RssInfo>> allSources;

	public MyRssReader() {
		this.allSources = new TreeMap<String, List<RssInfo>>();
	}

	public Map<String, List<RssInfo>> getAllSources() {
		return allSources;
	}

	public void setAllSources(Map<String, List<RssInfo>> allFeeds) {
		this.allSources = allFeeds;
	}

	@SuppressWarnings("unchecked")
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
		final List<SyndEntryImpl> entries = feed.getEntries();
		List<RssInfo> allFeeds = new ArrayList<>();
		for (SyndEntryImpl syndEntryImpl : entries) {

			RssInfo currentFeed = new RssInfo();

			currentFeed.setTitle(syndEntryImpl.getTitle());
			currentFeed.setAuthor(syndEntryImpl.getAuthor());
			currentFeed.setUri(syndEntryImpl.getUri());
			currentFeed.setDescription(syndEntryImpl.getDescription());
			currentFeed.setImageUrl(feed.getImage().getUrl());
			currentFeed.setPubDate(syndEntryImpl.getPublishedDate());
			final List enclosures = syndEntryImpl.getEnclosures();
			currentFeed.setMedia(enclosures);
			allFeeds.add(currentFeed);
			Collections.sort(allFeeds);
		}
		getAllSources().put(feed.getTitle(), allFeeds);
	}

	/**
	 * Removes the selected source
	 * 
	 * @return - true if removed or false if it is not removed or does not exist
	 */
	@Override
	public boolean removeFeedSorce(String title) {
		final List<RssInfo> remove = getAllSources().remove(title);
		return remove != null ? true : false;
	}

	@Override
	public List<String> displayAllSources() {
		List<String> allSources = new ArrayList<>();
		for (Entry<String, List<RssInfo>> entry : getAllSources().entrySet()) {
			allSources.add(entry.getKey());
		}
		return allSources;

	}

	@Override
	public List<RssInfo> displayAllNews() {
		List<RssInfo> allNews = new ArrayList<>();
		for (Entry<String, List<RssInfo>> entry : getAllSources().entrySet()) {
			allNews.addAll(entry.getValue());
		}
		Collections.sort(allNews);
		return allNews;

	}

	@Override
	public List<RssInfo> displayingOnlyUnreadNews() {
		List<RssInfo> allUnreadNews = new ArrayList<RssInfo>();
		for (Entry<String, List<RssInfo>> entry : getAllSources().entrySet()) {
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
