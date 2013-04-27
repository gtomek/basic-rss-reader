package uk.org.tomek.rssreader.items;

public interface FeedItem {

	public String getTitle();
	public String getDescription();
	public String getTrackName();
	public String getTrackArtist();
	public String getTrackId();
	public String getLink();
	public String getGuid();
	public String getPubDate();
	
}
