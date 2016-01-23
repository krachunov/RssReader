package edu.pragmatic.java.advanced.rss.project.model;

import com.sun.syndication.feed.synd.SyndContent;

public class RssInfo {
	private String uri;
	private String title;
	private String author;
	private SyndContent description;
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

	public SyndContent getDescription() {
		return description;
	}

	public void setDescription(SyndContent description) {
		this.description = description;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	@Override
	public String toString(){
		return String.format("Title: %s\nAuthor: %s.\nDescription: %s ",title,author,description);
		
	}

}
