package uk.org.tomek.rssreader.reader;

import java.util.List;

import uk.org.tomek.rssreader.items.FeedItem;

public final class RssReaderImpl implements RssReader {

	private final String mFeedUrl;

	public RssReaderImpl(String feedUrl) {
		mFeedUrl = feedUrl;
	}
	
	public static RssReader newInstance(String feedUrl) {
		return new RssReaderImpl(feedUrl);
	}

	@Override
	public List<FeedItem> getFeeds() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
