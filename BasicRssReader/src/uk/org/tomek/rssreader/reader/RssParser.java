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
	
	private RssParser() {
		mItemsList = new ArrayList<FeedItem>();
		Log.d(TAG, "RssParser ceated");
	}
	
	public static RssParser newInstance() {
		return new RssParser();
	}

	public List<FeedItem> getItems() {
		Log.d(TAG, "Called getItems()");
		return mItemsList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		Log.d(TAG, "Called startElement()");
		mStringBuilderTemp = new StringBuilder();
		if ("item".equalsIgnoreCase(qName)) {
			mCurrentItemBuilder = FeedItemImpl.createEmptyBuilder();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		Log.d(TAG, "Called endElement()");
		if(localName.equalsIgnoreCase("title")) {
			Log.d(TAG, "detected title");
			mCurrentItemBuilder.setTitle(mStringBuilderTemp.toString());
		}
		if ("item".equalsIgnoreCase(qName)) {
			FeedItemImpl currentItem = mCurrentItemBuilder.build();
			mItemsList.add(currentItem);
			mCurrentItemBuilder = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		mStringBuilderTemp.append(ch, start, length);
	}
}
