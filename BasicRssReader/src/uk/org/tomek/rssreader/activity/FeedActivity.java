package uk.org.tomek.rssreader.activity;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.items.FeedItem;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

public class FeedActivity extends Activity {

	 // This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;
    
    static final String TAG = "FeedActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);

		// Create ListView
		ListView itemsList = (ListView) findViewById(R.id.feed_list);
		// Create a list adapter
//		ArrayAdapter<FeedItem> adapter = new ArrayAdapter<FeedItem>(this,
//				android.R.layout.list_content, rssReader.getItems());
		// Set list adapter for the ListView
//		itemsList.setAdapter(adapter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

}
