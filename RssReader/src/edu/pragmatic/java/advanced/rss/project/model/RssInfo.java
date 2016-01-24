package edu.pragmatic.java.advanced.rss.project.model;

import java.util.Date;

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

	protected void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	protected void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	protected void setPubDate(Date pubDate) {
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
		return String.format(
				"Date: %s\nTitle: %s\nAuthor: %s\nDescription: %s ",
				pubDate.toString(), title, author, description);
	}

	@Override
	public int compareTo(RssInfo o) {
		return getPubDate().compareTo(o.getPubDate());
	}
}
