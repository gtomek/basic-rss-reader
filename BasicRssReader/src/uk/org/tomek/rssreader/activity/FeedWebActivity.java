package uk.org.tomek.rssreader.activity;

import uk.org.tomek.rssreader.config.Configuration;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

public class FeedWebActivity extends Activity {

	private static final String TAG = "FeedWebActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		WebView webView = new WebView(this);
		setContentView(webView);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		String link = getIntent().getStringExtra(Configuration.LINK_TAG);
		if (link != null) {
			webView.loadUrl(link);
		} else {
			Log.e(TAG, "The link cannot be open, opening shazam.com instead!");
			webView.loadUrl("www.shazam.com");
		}
	}
	
	/**
	 * Handles clicking on back caret (acting as back button).
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
