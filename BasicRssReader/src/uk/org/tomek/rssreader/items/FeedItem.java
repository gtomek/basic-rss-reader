package uk.org.tomek.rssreader.items;

import android.os.Parcel;
import android.os.Parcelable;

public final class FeedItem implements Parcelable{
	
	private String mTitle;
	private String mDescription;
	private String mTrackName;
	private String mTrackArtist;
	private String mTrackId;
	private String mLink;
	private String mGuid;
	private String mPubDate;
	
	/**
	 * Private constructor.
	 * 
	 * @param title
	 * @param description
	 * @param trackName
	 * @param trackArtist
	 * @param trackId
	 * @param link
	 * @param guid
	 * @param pubDate
	 */
	private FeedItem(String title, String description, String trackName, String trackArtist,
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
	
	/**
	 * Parcelable constructor.
	 * 
	 * @param in
	 */
	private FeedItem(Parcel in) {
		String[] inData = new String[8];

        in.readStringArray(inData);
        mTitle = inData[0];
        mDescription = inData[1];
        mTrackName = inData[2];
        mTrackArtist = inData[3];
        mTrackId = inData[4];
        mLink = inData[5];
        mGuid = inData[6];
        mPubDate = inData[7];
        
	}

	public String getTitle() {
		return mTitle;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getTrackName() {
		return mTrackName;
	}

	public String getTrackArtist() {
		return mTrackArtist;
	}

	public String getTrackId() {
		return mTrackId;
	}

	public String getLink() {
		return mLink;
	}

	public String getGuid() {
		return mGuid;
	}

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

		public FeedItem build() {
			return new FeedItem(mTitle, mDescription, mTrackName, mTrackArtist, mTrackId,
					mLink, mGuid, mPubDate);
		}
	}

	/**
	 * Parcelable creator.
	 */
	public static final Parcelable.Creator<FeedItem> CREATOR = new Parcelable.Creator<FeedItem>() {
		public FeedItem createFromParcel(Parcel in) {
			return new FeedItem(in);
		}

		public FeedItem[] newArray(int size) {
			return new FeedItem[size];
		}
	};
	
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { getTitle(), getDescription(), getTrackName(),
				getTrackArtist(), getTrackId(), getLink(), getGuid(), getPubDate() });
	}

}
