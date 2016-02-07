package edu.pragmatic.java.advanced.rss.project.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;

import edu.pragmatic.java.advanced.rss.project.model.MyRssReader;
import edu.pragmatic.java.advanced.rss.project.model.RssInfo;
import edu.pragmatic.java.advanced.rss.project.model.RssOption;

public class Controller {
	RssOption reader = new MyRssReader();

	List<String> displayAllSources = reader.displayAllSources();

	List<RssInfo> currentFeed = reader.displayAllNews();
	
	
	public void addSource(String url){
		
		SyndFeed feed = createFeed(url);
		final List<SyndEntryImpl> entries = feed.getEntries();

		List<RssInfo> allFeeds = new ArrayList<>();
		for (SyndEntryImpl syndEntryImpl : entries) {
//			RssInfo currentFeed = createRssInfoObject(feed, syndEntryImpl);
//			allFeeds.add(currentFeed);
		}
		Collections.sort(allFeeds);
		reader.addFeedSorce(url);
		System.out.println("add2 test");
		
			
	}
	public RssInfo createRssInfoObject(SyndFeed feed,
			SyndEntryImpl syndEntryImpl){
		
		return new RssInfo();
	}
	
	public SyndFeed createFeed(String url){
		return reader.createFeed(url);
				
	}
	 
	public List<RssInfo> displayAllNews() {

		return currentFeed;
	}
	public List<RssInfo> displayAllNews(Object index) {
		List<RssInfo> allNews = new ArrayList<>();
		for (RssInfo entry : getAllSources().get(index)) {
			allNews.add(entry);
		}
		Collections.sort(allNews);
		return allNews;

	}
	
	public String printDescription(String title){
		RssInfo rssInfo = null;
		for (int i = 0; i < currentFeed.size() ; i++){
			if (currentFeed.get(i).getTitle().equals(title)){
				rssInfo = currentFeed.get(i);
			}
		}
		
		return rssInfo.toString();
	}
	public RssInfo displaySelectedNews(int index) {
		RssInfo rssInfo = currentFeed.get(index);
				
		return rssInfo;

	}

	public String displayNewsDescription(RssInfo rssInfo) {
		RssInfo syndFeed = rssInfo;
		String rssDescrption = "";
		if (syndFeed != null) {
			if (syndFeed.getDescription() != null && !syndFeed.getDescription().equals("")) {
				String description = syndFeed.getDescription().toString();
				// out of description remove tags if any exist and store also
				// short description
				rssDescrption = description.replaceAll("\\<[^>]*>", "");
				rssDescrption = rssDescrption.replace("SyndContentImpl.mode=null", "");
				rssDescrption = rssDescrption.replace("SyndContentImpl.type=text/html", "");
				rssDescrption = rssDescrption.replace("SyndContentImpl.interface=interface com.sun.syndication.feed.synd.SyndContent", "");
				rssDescrption = rssDescrption.replace("SyndContentImpl.value=Your browser does not support iframes.", "");
				

			} else {
				rssDescrption = "NO DESCRIPTION AVAILABLE for FEED";
			}

		}
		return rssDescrption;
	}

	public Map<String, List<RssInfo>> getAllSources() {
		return reader.getAllSources();
	}

}
