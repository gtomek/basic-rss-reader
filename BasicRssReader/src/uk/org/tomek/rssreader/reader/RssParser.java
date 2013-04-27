package uk.org.tomek.rssreader.reader;

import org.xml.sax.helpers.DefaultHandler;

public final class RssParser extends DefaultHandler {

	public static RssParser newInstance() {
		return new RssParser();
	}
}
