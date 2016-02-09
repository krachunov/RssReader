/**
 * @author Krachunov
 */
package edu.pragmatic.java.advanced.rss.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class RssInfo implements Comparable<RssInfo> ,Serializable{
	private static final long serialVersionUID = -8605831172961667470L;
	private String uri;
	private String title;
	private String author;
	private SyndContent description;
	private String imageUrl;
	private List<SyndEntryImpl> media;
	private Date pubDate;
	private boolean isVisited;

	/**
	 * 
	 * @return - type String
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 
	 * @param uri
	 *            - String
	 */
	protected void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * 
	 * @return - type String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            - type String
	 */
	protected void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return - type String
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @param author
	 *            -type String
	 */
	protected void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * @return - type SyndContent
	 */
	public SyndContent getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            - type SyndContent
	 */
	protected void setDescription(SyndContent description) {
		this.description = description;
	}

	/**
	 * 
	 * @return - type SyndImage
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 
	 * @param image
	 *            - String
	 */
	public void setImageUrl(String image) {
		this.imageUrl = image;
	}

	/**
	 * 
	 * @return - type SyndEntryImpl
	 */
	public List<SyndEntryImpl> getMedia() {
		return media;
	}

	/**
	 * 
	 * @param media
	 *            - type SyndEntryImpl
	 */
	public void setMedia(List<SyndEntryImpl> media) {
		this.media = media;
	}

	/**
	 * 
	 * @return - type Date
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * 
	 * @param pubDate
	 *            - description - type Date
	 */
	protected void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * 
	 * @return - type boolean
	 */
	public boolean isVisited() {
		return isVisited;
	}

	/**
	 * 
	 * @param isVisited
	 *            - type boolean
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	/**
	 * Get String value from all fields
	 */
	@Override
	public String toString() {
		return String
				.format("Date: %s\nTitle: %s\nAuthor: %s\nDescription: %s\nImage: %s\nMedia: %s",
						pubDate.toString(), title, author,
						description.getValue(), imageUrl, media);
	}

	public int compareTo(RssInfo o) {
		return getPubDate().compareTo(o.getPubDate());
	}
}
