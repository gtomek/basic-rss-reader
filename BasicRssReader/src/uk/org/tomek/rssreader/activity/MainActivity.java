package uk.org.tomek.rssreader.activity;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.reader.DownloadFeedsTask;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main Activity launching Basic Rss Reader.
 *  
 * @author Tomasz Giszczak <tgiszczak@gmail.com>
 */
public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";
	
	// TODO: URL should be configurable somewhere in the app
	// for the moment the URL of the feed is fixed
	private final String RSS_FEED_URL = 
			"http://www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		Button readButton = (Button) findViewById(R.id.read_button);
		readButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DownloadFeedsTask().execute(RSS_FEED_URL);
			}
			
		});
		
		return true;
	}

}
