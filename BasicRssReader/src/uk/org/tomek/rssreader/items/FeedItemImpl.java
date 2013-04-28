package uk.org.tomek.rssreader.items;


public class FeedItemImpl implements FeedItem{
	
	private final String mTitle;
	private final String mDescription;
	private final String mTrackName;
	private final String mTrackArtist;
	private final String mTrackId;
	private final String mLink;
	private final String mGuid;
	private final String mPubDate;
	
	private FeedItemImpl(String title, String description, String trackName, String trackArtist,
			String trackId, String link, String guid, String pubDate) {
		mTitle = title;
		mDescription = description;
		mTrackName = trackName;
		mTrackArtist = trackArtist;
		mTrackId = trackId;
		mLink = link;
		mGuid = guid;
		mPubDate = pubDate;
	}

	@Override
	public String getTitle() {
		return mTitle;
	}

	@Override
	public String getDescription() {
		return mDescription;
	}

	@Override
	public String getTrackName() {
		return mTrackName;
	}

	@Override
	public String getTrackArtist() {
		return mTrackArtist;
	}

	@Override
	public String getTrackId() {
		return mTrackId;
	}

	@Override
	public String getLink() {
		return mLink;
	}

	@Override
	public String getGuid() {
		return mGuid;
	}

	@Override
	public String getPubDate() {
		return mPubDate;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("mTitle=").append(mTitle).append(",")
		.append("mDescription=").append(mDescription).append(",")
		.append("mTrackName=").append(mTrackName).append(",")
		.append("mTrackArtist=").append(mTrackArtist).append(",")
		.append("mTrackId=").append(mTrackId).append(",")
		.append("mLink=").append(mLink).append(",")
		.append("mGuid=").append(mGuid).append(",")
		.append("mPubDate=").append(mPubDate).append(",");
		return result.toString();
	};
	
	public static Builder createEmptyBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private String mTitle;
		private String mDescription;
		private String mTrackName;
		private String mTrackArtist;
		private String mTrackId;
		private String mLink;
		private String mGuid;
		private String mPubDate;
		
		private Builder() {
		}

		public void setTitle(String title) {
			this.mTitle = title;
		}

		public void setDescription(String description) {
			this.mDescription = description;
		}

		public void setTrackName(String trackName) {
			this.mTrackName = trackName;
		}

		public void setTrackArtist(String trackArtist) {
			this.mTrackArtist = trackArtist;
		}

		public void setTrackId(String trackId) {
			this.mTrackId = trackId;
		}

		public void setLink(String link) {
			this.mLink = link;
		}

		public void setGuid(String guid) {
			this.mGuid = guid;
		}

		public void setPubDate(String pubDate) {
			this.mPubDate = pubDate;
		}

		public FeedItemImpl build() {
			return new FeedItemImpl(mTitle, mDescription, mTrackName, mTrackArtist, mTrackId,
					mLink, mGuid, mPubDate);
		}
	}

}
