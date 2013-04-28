package uk.org.tomek.rssreader.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.tomek.rssreader.R;
import uk.org.tomek.rssreader.config.Configuration;
import uk.org.tomek.rssreader.items.FeedItem;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Activity that displays the list of fetched items.
 * 
 * @author Tomasz Giszczak <tgiszczak@gmail.com>
 * 
 */
public class FeedActivity extends Activity {

	 // This is the Adapter being used to display the list's data
    SimpleCursorAdapter mAdapter;
    
    static final String TAG = "FeedActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);

		List<FeedItem> feedsList = getIntent().getParcelableArrayListExtra(Configuration.TAG_ITEMS_ARRAY);

		// create ArrayList of Maps for SimpleAdapter 
		List<Map<String, String>> rssItemsList = getArrayListOfItemMaps(feedsList);
		
		// Create ListView
		ListView itemsListView = (ListView) findViewById(R.id.feed_list);

		// TextView elements in feed_item
		int[] toViews = { R.id.artist, R.id.title }; 

		SimpleAdapter adapter = new SimpleAdapter(this, rssItemsList, R.layout.feed_item,
				new String[] { "artist", "title" }, toViews);
		
		// Set list adapter for the ListView
		itemsListView.setAdapter(adapter);
	}

	/**
	 * Creates List of Maps for SimpleAdapter.
	 * 
	 * @param feedsList
	 * @return List of items Maps
	 */
	private List<Map<String, String>> getArrayListOfItemMaps(List<FeedItem> feedsList) {
		List<Map<String, String>> rssItemsList = new ArrayList<Map<String, String>>();
		for (FeedItem item : feedsList) {
			Map<String, String> itemsMap = new HashMap<String, String>();
			itemsMap.put("artist", item.getTrackArtist());
			itemsMap.put("title", item.getTitle());
			rssItemsList.add(itemsMap);
		}
		return rssItemsList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

}
