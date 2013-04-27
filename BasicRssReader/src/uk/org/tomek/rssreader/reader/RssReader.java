package uk.org.tomek.rssreader.reader;

import java.util.List;

import uk.org.tomek.rssreader.items.FeedItem;

public interface RssReader {
	
	public List<FeedItem> getFeeds();
}
