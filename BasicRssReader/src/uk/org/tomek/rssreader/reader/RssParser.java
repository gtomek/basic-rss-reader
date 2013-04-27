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
		Log.d(TAG, "Called getItems()");
		return mItemsList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException {
		Log.d(TAG, String.format("Element:%s start detected, localName:%s", qName, localName));
		if ("item".equalsIgnoreCase(qName)) {
			mCurrentItemBuilder = FeedItemImpl.createEmptyBuilder();
			mIsInItemTag = true;
			mStringBuilderTemp.setLength(0);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		Log.d(TAG, String.format("Element:%s end detected, localName:%s", qName, localName));
		if ("item".equalsIgnoreCase(qName)) {
			FeedItemImpl currentItem = mCurrentItemBuilder.build();
			mItemsList.add(currentItem);
			mCurrentItemBuilder = null;
			mIsInItemTag = false;
		}
		if (mIsInItemTag) {
			if (qName.equalsIgnoreCase("title")) {
				Log.d(TAG, String.format("detected title:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setTitle(mStringBuilderTemp.toString());
			}
			if (qName.equalsIgnoreCase("description")) {
				Log.d(TAG, String.format("detected description:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setDescription(mStringBuilderTemp.toString());
			}
			if (qName.equalsIgnoreCase("trackName")) {
				Log.d(TAG, String.format("detected track name:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setTrackName(mStringBuilderTemp.toString());
			}
			if (qName.equalsIgnoreCase("trackArtist")) {
				Log.d(TAG, String.format("detected artist:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setTrackArtist(mStringBuilderTemp.toString());
			}
			if (qName.equalsIgnoreCase("trackId")) {
				Log.d(TAG, String.format("detected trackId:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setTrackId(mStringBuilderTemp.toString());
			}
			if (qName.equalsIgnoreCase("link")) {
				Log.d(TAG, String.format("detected link:%s", mStringBuilderTemp.toString()));
				mCurrentItemBuilder.setLink(mStringBuilderTemp.toString());
			}
			mStringBuilderTemp.setLength(0);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		mStringBuilderTemp.append(ch, start, length);
//		Log.d(TAG, String.format("String builder value:%s", mStringBuilderTemp.toString()));
	}
}
