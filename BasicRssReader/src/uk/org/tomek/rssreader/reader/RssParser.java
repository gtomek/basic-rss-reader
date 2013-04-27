package uk.org.tomek.rssreader.reader;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.org.tomek.rssreader.items.FeedItem;
import uk.org.tomek.rssreader.items.FeedItemImpl;
import android.util.Log;

public final class RssParser extends DefaultHandler {

	private final String TAG = "RssParser";
	private List<FeedItem> mItemsList;
	private FeedItemImpl.Builder mCurrentItemBuilder;
	private StringBuilder mStringBuilderTemp;
	private boolean mIsInItemTag = false;
	
	private RssParser() {
		mItemsList = new ArrayList<FeedItem>();
		mStringBuilderTemp = new StringBuilder();
		Log.d(TAG, "RssParser ceated");
	}
	
	public static RssParser newInstance() {
		return new RssParser();
	}

	public List<FeedItem> getItems() {
		return mItemsList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		if ("item".equalsIgnoreCase(qName)) {
			mCurrentItemBuilder = FeedItemImpl.createEmptyBuilder();
			mIsInItemTag = true;
			mStringBuilderTemp.setLength(0);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equalsIgnoreCase(qName)) {
			FeedItemImpl currentItem = mCurrentItemBuilder.build();
			mItemsList.add(currentItem);
			mCurrentItemBuilder = null;
			mIsInItemTag = false;
		}
		if (mIsInItemTag) {
			if (qName.equalsIgnoreCase("title")) {
				mCurrentItemBuilder.setTitle(extractCleanString());
			}
			if (qName.equalsIgnoreCase("description")) {
				mCurrentItemBuilder.setDescription(extractCleanString());
			}
			if (qName.equalsIgnoreCase("trackName")) {
				mCurrentItemBuilder.setTrackName(extractCleanString());
			}
			if (qName.equalsIgnoreCase("trackArtist")) {
				mCurrentItemBuilder.setTrackArtist(extractCleanString());
			}
			if (qName.equalsIgnoreCase("trackId")) {
				mCurrentItemBuilder.setTrackId(extractCleanString());
			}
			if (qName.equalsIgnoreCase("link")) {
				mCurrentItemBuilder.setLink(extractCleanString());
			}
			mStringBuilderTemp.setLength(0);
		}
	}

	/**
	 * Helper method removing training spaces and new line characters from the parsed String.
	 * 
	 * @return
	 */
	private String extractCleanString() {
		return mStringBuilderTemp.toString().replaceAll("(\\r|\\n)", "").trim();
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		mStringBuilderTemp.append(ch, start, length);
	}
}
