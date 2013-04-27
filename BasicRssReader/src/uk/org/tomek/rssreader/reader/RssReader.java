package uk.org.tomek.rssreader.reader;

import java.util.List;

import uk.org.tomek.rssreader.items.FeedItem;

public interface RssReader {
	
	/**
	 * Connects to the server and launches data parsing.
	 * 
	 * @return list of received RSS feeds.
	 */
	public List<FeedItem> getFeeds();
}
