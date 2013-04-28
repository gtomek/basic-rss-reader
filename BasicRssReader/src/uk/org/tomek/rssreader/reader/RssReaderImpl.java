package uk.org.tomek.rssreader.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uk.org.tomek.rssreader.items.FeedItem;
import android.util.Log;

/**
 * Implementation of {@link RssReader}.
 * 
 * @author Tomek Giszczak <tgiszczak@gmail.com>
 *
 */
public final class RssReaderImpl implements RssReader {

	private final String mFeedUrl;
	private final String TAG = "RssReaderImpl";

	public RssReaderImpl(String feedUrl) {
		mFeedUrl = feedUrl;
		Log.d(TAG, String.format("RssReaderImpl created with URL:%s", feedUrl));
	}
	
	public static RssReader newInstance(String feedUrl) {
		return new RssReaderImpl(feedUrl);
	}

	@Override
	public ArrayList<FeedItem> getFeeds() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		RssParser parserHandler = RssParser.newInstance();
		try {
			// initialising new SAX parser
			saxParser = factory.newSAXParser();
			
			// launching http connection
			URL url = new URL(mFeedUrl);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
			InputSource is = new InputSource();
			
			// setting encoding
			is.setCharacterStream(isr);
			// parsing received XML feed 
			saxParser.parse(is, parserHandler);
		} catch (ParserConfigurationException e) {
			Log.e(TAG, "ParserConfigurationException");
			e.printStackTrace();
		} catch (SAXException e) {
			Log.e(TAG, "SAXException");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(TAG, "IOException");
			e.printStackTrace();
		}
	
		return parserHandler.getItems();
	}

	
}
