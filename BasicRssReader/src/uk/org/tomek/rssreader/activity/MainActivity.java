package uk.org.tomek.rssreader.activity;

import java.util.ArrayList;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.items.FeedItem;
import uk.org.tomek.rssreader.reader.RssReader;
import uk.org.tomek.rssreader.reader.RssReaderImpl;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Main Activity launching Basic Rss Reader.
 *  
 * @author Tomasz Giszczak <tgiszczak@gmail.com>
 */
public class MainActivity extends Activity {
	
	public static final String TAG_ITEMS_ARRAY = "TAG_ITEMS_ARRAY";
	// TODO: URL should be configurable somewhere in the app
	// for the moment the URL of the feed is fixed
	private final String RSS_FEED_URL = 
			"http://www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam";
	private ProgressBar mProgressBar;
	private TextView mProgressBarDescription;
	private Button mRetryButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mProgressBarDescription = (TextView) findViewById(R.id.progress_bar_description);
		
		mRetryButton = (Button) findViewById(R.id.retry_button);
		mRetryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DownloadFeedsTask().execute(RSS_FEED_URL);
			}
			
		});
		
		new DownloadFeedsTask().execute(RSS_FEED_URL);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	/**
	 * {@link AsyncTask} responsible for fetching and parsing of RSS feeds.
	 * 
	 * @author Tomasz Giszczak <tgiszczak@gmail.com>
	 */
	private class DownloadFeedsTask extends AsyncTask<String, Boolean, ArrayList<FeedItem>> {

		private String TAG = "DownloadFeedsTask";

		@Override
		protected ArrayList<FeedItem> doInBackground(String... url) {

			// Initialise RssReader
			RssReader rssReader = RssReaderImpl.newInstance(url[0]);
			Log.d(TAG , "Launching read feeds");
			ArrayList<FeedItem> resultList = rssReader.getFeeds();
			if (resultList != null && !resultList.isEmpty()){
				publishProgress(true);
				return resultList;
			} else {
				publishProgress(false);
				return null;
			}
			
		}
		
		@Override
		protected void onProgressUpdate(Boolean... values) {
			if (values[0]) {
				Log.d(TAG , "Fetching and parsing feeds finished successfully.");
				mProgressBar.setVisibility(View.INVISIBLE);
				mProgressBarDescription.setText(R.string.fetch_success);
			} else {
				Log.e(TAG , "Fetching and parsing feeds has failed.");
				mRetryButton.setVisibility(View.VISIBLE);
				mProgressBarDescription.setText(R.string.fetch_failure);
			}
		}
		
		@Override
		protected void onPostExecute(ArrayList<FeedItem> result) {
			// Display the content..
			Intent feedPresentation = new Intent(MainActivity.this, FeedActivity.class);
//			feedPresentation.putExtra(TAG_ITEMS_ARRAY, result);
			startActivity(feedPresentation);
		}
	}


}
