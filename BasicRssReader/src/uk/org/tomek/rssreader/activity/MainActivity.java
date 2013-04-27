package uk.org.tomek.rssreader.activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.reader.RssReader;
import uk.org.tomek.rssreader.reader.RssReaderImpl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main Activity launching Basic Rss Reader.
 *  
 * @author Tomek Giszczak <tgiszczak@gmail.com>
 */
public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	// TODO: URL should be configurable somewhere in the app
	// for the moment the URL of the feed is fixed
	private final String RSS_FEED_URL = 
			"http://www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam";
	
	private ExecutorService executorService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		executorService = Executors.newSingleThreadExecutor();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		// Initialise RssReader
		final RssReader rssReader = RssReaderImpl.newInstance(RSS_FEED_URL);
		
		Button readButton = (Button) findViewById(R.id.read_button);
		readButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Launching read feeds");
				executorService.execute(new Runnable() {
					
					@Override
					public void run() {
						rssReader.getFeeds();
						
					}
				});
			}
		});
		
		return true;
	}

}
