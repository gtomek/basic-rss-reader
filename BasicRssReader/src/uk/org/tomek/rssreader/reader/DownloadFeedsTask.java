package uk.org.tomek.rssreader.reader;

import java.util.List;

import uk.org.tomek.rssreader.items.FeedItem;
import android.os.AsyncTask;
import android.util.Log;

/**
 * {@link AsyncTask} responsible for connection to the RSS feed server and parsing of the content.
 * 
 * @author Tomasz Giszczak <tgiszczak@gmail.com>
 * 
 */
public class DownloadFeedsTask extends AsyncTask<String, Boolean, List<FeedItem>> {

	private String TAG = "DownloadFeedsTask";

	@Override
	protected List<FeedItem> doInBackground(String... url) {

		// Initialise RssReader
		RssReader rssReader = RssReaderImpl.newInstance(url[0]);
		Log.d(TAG , "Launching read feeds");
		List<FeedItem> resultList = rssReader.getFeeds();
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
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(List<FeedItem> result) {
		// TODO Display the content..
		super.onPostExecute(result);
	}

	

}
