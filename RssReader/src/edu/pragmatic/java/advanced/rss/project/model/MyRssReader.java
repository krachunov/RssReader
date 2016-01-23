package edu.pragmatic.java.advanced.rss.project.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class MyRssReader implements RssOption {
	private List<RssInfo> allFeeds;

	public MyRssReader(List<RssInfo> allFeeds) {
		this.allFeeds = new ArrayList<>();
	}

	public List<RssInfo> getAllFeeds() {
		return allFeeds;
	}

	public void setAllFeeds(List<RssInfo> allFeeds) {
		this.allFeeds = allFeeds;
	}

	public MyRssReader() {
		this.allFeeds = new ArrayList<RssInfo>();
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
		for (Object object : entries) {
			final SyndEntryImpl syndEntryImpl = (SyndEntryImpl) object;
			RssInfo currentFeed = new RssInfo();

			currentFeed.setTitle(syndEntryImpl.getTitle());
			currentFeed.setAuthor(syndEntryImpl.getAuthor());
			currentFeed.setUri(syndEntryImpl.getUri());
			currentFeed.setDescription(((SyndEntryImpl) object)
					.getDescription());

			getAllFeeds().add(currentFeed);
		}

	}

	@Override
	public void removeFeedSorce() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllSorces() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayAllNews() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayingOnlyUnreadNews() {
		// TODO Auto-generated method stub

	}

}
