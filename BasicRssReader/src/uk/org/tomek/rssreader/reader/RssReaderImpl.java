package uk.org.tomek.rssreader.reader;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		DefaultHandler parserHandler = RssParser.newInstance();
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(mFeedUrl, parserHandler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}

	
}
