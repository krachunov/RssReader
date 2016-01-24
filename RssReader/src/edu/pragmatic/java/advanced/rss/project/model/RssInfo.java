package edu.pragmatic.java.advanced.rss.project.model;

import java.util.Date;

import com.sun.syndication.feed.synd.SyndContent;

//TODO - add comparision on date
public class RssInfo implements Comparable<RssInfo> {
	private String uri;
	private String title;
	private String author;
	private String description;
	private Date pubDate;
	private boolean isVisited;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	@Override
	public String toString() {
		return String.format("Date: %s\nTitle: %s\nAuthor: %s\nDescription: %s ",pubDate.toString(), title,
				author, description);

	}

	@Override
	public int compareTo(RssInfo o) {
		return getPubDate().compareTo(o.getPubDate());
	}

}
