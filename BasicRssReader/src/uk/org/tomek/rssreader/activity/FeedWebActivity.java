/**
 *   Copyright 2013 Tomasz Giszczak <tgiszczak@gmail.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package uk.org.tomek.rssreader.activity;

import uk.org.tomek.rssreader.config.Configuration;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Activity responsible for opening a web page pointing out at the link retrieved from the RSS feed.
 * 
 * @author Tomasz Giszczak <tgiszczak@gmail.com>
 * 
 */
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
