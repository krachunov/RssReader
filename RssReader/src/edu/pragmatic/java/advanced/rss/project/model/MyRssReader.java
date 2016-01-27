/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
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

public class MyRssReader implements RssOption, Serializable {
	static final String FILE_TO_LOAD = "clientSetings";
	private static final long serialVersionUID = 1L;
	private Map<String, List<RssInfo>> allSources;

	public MyRssReader() {
		loadPreviewSession();
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

		SyndFeed feed = createFeed(url);
		final List<SyndEntryImpl> entries = feed.getEntries();

		List<RssInfo> allFeeds = new ArrayList<>();
		for (SyndEntryImpl syndEntryImpl : entries) {
			RssInfo currentFeed = createRssInfoObject(feed, syndEntryImpl);
			allFeeds.add(currentFeed);
		}
		Collections.sort(allFeeds);
		getAllSources().put(feed.getTitle(), allFeeds);
	}

	private SyndFeed createFeed(String url) {
		URL feedSource = null;
		try {
			feedSource = new URL(url);
		} catch (MalformedURLException e) {
			createLogFile(e);
			e.printStackTrace();
		}
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(feedSource));
		} catch (IllegalArgumentException e) {
			createLogFile(e);
			e.printStackTrace();
		} catch (FeedException e) {
			createLogFile(e);
			e.printStackTrace();
		} catch (IOException e) {
			createLogFile(e);
			e.printStackTrace();
		}
		return feed;
	}

	private RssInfo createRssInfoObject(SyndFeed feed,
			SyndEntryImpl syndEntryImpl) {
		RssInfo currentFeed = new RssInfo();

		currentFeed.setTitle(syndEntryImpl.getTitle());
		currentFeed.setAuthor(syndEntryImpl.getAuthor());
		currentFeed.setUri(syndEntryImpl.getUri());
		currentFeed.setDescription(syndEntryImpl.getDescription());
		currentFeed.setImageUrl(feed.getImage().getUrl());
		currentFeed.setPubDate(syndEntryImpl.getPublishedDate());
		final List enclosures = syndEntryImpl.getEnclosures();
		currentFeed.setMedia(enclosures);
		return currentFeed;
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

	public void serialize() {

		File file = new File(FILE_TO_LOAD);
		FileOutputStream fileOutput = null;
		try {
			fileOutput = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			createLogFile(e);
			e.printStackTrace();
		}
		ObjectOutputStream objectOutput = null;
		try {
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(getAllSources());
		} catch (IOException e) {
			createLogFile(e);
			e.printStackTrace();
		} finally {

			try {
				if (fileOutput != null) {
					fileOutput.close();
				}
				if (objectOutput != null) {
					objectOutput.close();
				}
			} catch (IOException e) {
				createLogFile(e);
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, List<RssInfo>> deserialize(String fileToDeserialize) {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(fileToDeserialize);
		} catch (FileNotFoundException e) {
			createLogFile(e);
			e.printStackTrace();
		}
		ObjectInputStream objectImput = null;
		try {
			objectImput = new ObjectInputStream(fileInput);
		} catch (IOException e) {
			createLogFile(e);
			e.printStackTrace();
		}
		try {
			Map<String, List<RssInfo>> deserializeClient = null;
			try {
				deserializeClient = (Map<String, List<RssInfo>>) objectImput
						.readObject();
			} catch (ClassNotFoundException e) {
				createLogFile(e);
				e.printStackTrace();
			} catch (IOException e) {
				createLogFile(e);
				e.printStackTrace();
			}
			return deserializeClient;
		} finally {
			try {
				if (fileInput != null) {
					fileInput.close();
				}
				if (objectImput != null) {
					objectImput.close();
				}
			} catch (IOException e) {
				createLogFile(e);
				e.printStackTrace();
			}

		}
	}

	private void loadPreviewSession() {
		if (chekFileExist(FILE_TO_LOAD)) {
			setAllSources(deserialize(FILE_TO_LOAD));
		} else {
			setAllSources(new TreeMap<String, List<RssInfo>>());
		}
	}

	private static boolean chekFileExist(String fileName) {
		File file = new File(fileName);
		if (file.exists() && !file.isDirectory()) {
			return true;
		}
		return false;
	}

	private static void createLogFile(Exception e) {
		String errorLogFileName = "errorLog.log";
		Writer writer = null;
		try {
			writer = new FileWriter(errorLogFileName, true);
		} catch (IOException e1) {
			e1.printStackTrace(new PrintWriter(new BufferedWriter(writer), true));
		}
		e.printStackTrace(new PrintWriter(new BufferedWriter(writer), true));
	}
}
