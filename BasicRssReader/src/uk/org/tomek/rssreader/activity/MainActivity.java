package uk.org.tomek.rssreader.activity;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.reader.RssReaderImpl;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Main Activity launching Basic Rss Reader.
 *  
 * @author Tomek Giszczak <tgiszczak@gmail.com>
 */
public class MainActivity extends Activity {
	
	// TODO: URL should be configurable somewhere in the app
	// for the moment the URL of the feed is fixed
	private final String RSS_FEED_URL = "www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		// Initialise RssReader
		RssReaderImpl.newInstance(RSS_FEED_URL);
		
		return true;
	}

}
