// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Playable;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.os.Parcelable;

public class Asset implements Parcelable, PlayContext
{
    public static final Parcelable$Creator<Asset> CREATOR;
    private static final String NULL = "=NULL=";
    public static final String PARAM_DURATION = "duration";
    public static final String PARAM_ENDTIME = "endtime";
    public static final String PARAM_EP_BOOKMARK = "playback_bookmark";
    public static final String PARAM_EP_BOOKMARK_TS = "watched_date";
    public static final String PARAM_EP_EPISODE_NUMBER = "episodeNumber";
    public static final String PARAM_EP_SEASON_NUMBER = "seasonNumber";
    public static final String PARAM_FACEBOOK_DONOT_SHARE = "fb_dontsharebtn";
    public static final String PARAM_IS_AUTOPLAY = "isAutoPlayEnabled";
    public static final String PARAM_IS_EPISODE = "isEpisode";
    public static final String PARAM_IS_NEXT_EPISODE = "isNextPlayableEpisode";
    public static final String PARAM_LIST_POS = "listpos";
    public static final String PARAM_ORIGINAL_URL = "nflx";
    public static final String PARAM_PARENT_ID = "parentid";
    public static final String PARAM_PARENT_TITLE = "parent_title";
    public static final String PARAM_PLAYABLE_ID = "playableid";
    public static final String PARAM_POSTPLAY_COUNT = "postPlayCount";
    public static final String PARAM_REQ_ID = "reqid";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_TRK_ID = "trkid";
    public static final String PARAM_VIDEO_POS = "videopos";
    private static final String TAG = "mdxui";
    private int mDuration;
    private int mEndtime;
    private int mEpisodeNumber;
    private boolean mIsAutoPlayEnabled;
    private boolean mIsEpisode;
    private boolean mIsNextPlayableEpisode;
    private int mListPos;
    private String mNflx;
    private String mParentId;
    private String mParentTitle;
    private String mPlayableId;
    private int mPlaybackBookmark;
    private int mPostPlayVideoPlayed;
    private String mReqId;
    private int mSeasonNumber;
    private boolean mSocialDoNotShareVisible;
    private String mTitle;
    private int mTrackId;
    private int mVideoPos;
    private long mWatchedDate;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Asset>() {
            public Asset createFromParcel(final Parcel parcel) {
                return new Asset(parcel, null);
            }
            
            public Asset[] newArray(final int n) {
                return new Asset[n];
            }
        };
    }
    
    private Asset() {
    }
    
    private Asset(final Parcel parcel) {
        this.mPlayableId = readString(parcel);
        this.mParentId = readString(parcel);
        this.mTrackId = readInt(parcel);
        this.mIsEpisode = readBoolean(parcel);
        this.mTitle = readString(parcel);
        this.mParentTitle = readString(parcel);
        this.mWatchedDate = readLong(parcel);
        this.mPlaybackBookmark = readInt(parcel);
        this.mNflx = readString(parcel);
        this.mSocialDoNotShareVisible = readBoolean(parcel);
        this.mSeasonNumber = readInt(parcel);
        this.mEpisodeNumber = readInt(parcel);
        this.mDuration = readInt(parcel);
        this.mReqId = readString(parcel);
        this.mListPos = readInt(parcel);
        this.mVideoPos = readInt(parcel);
        this.mEndtime = readInt(parcel);
        this.mIsAutoPlayEnabled = readBoolean(parcel);
        this.mIsNextPlayableEpisode = readBoolean(parcel);
        this.mPostPlayVideoPlayed = readInt(parcel);
    }
    
    public static Asset create(final Playable playable, final PlayContext playContext) {
        if (Log.isLoggable("mdxui", 2)) {
            Log.v("mdxui", "ASSET: create asset from playable " + playable);
        }
        final Asset asset = new Asset();
        if (playable != null) {
            asset.mPlayableId = playable.getPlayableId();
            asset.mParentId = playable.getParentId();
            asset.mTitle = playable.getPlayableTitle();
            asset.mParentTitle = playable.getParentTitle();
            asset.mPlaybackBookmark = playable.getPlayableBookmarkPosition();
            asset.mWatchedDate = playable.getPlayableBookmarkUpdateTime();
            asset.mDuration = playable.getRuntime();
            asset.mEndtime = playable.getEndtime();
            asset.mSeasonNumber = playable.getSeasonNumber();
            asset.mEpisodeNumber = playable.getEpisodeNumber();
            asset.mIsEpisode = playable.isPlayableEpisode();
            asset.mSocialDoNotShareVisible = playable.getFbDntShare();
            asset.mIsAutoPlayEnabled = playable.isAutoPlayEnabled();
            asset.mIsNextPlayableEpisode = playable.isNextPlayableEpisode();
        }
        if (playContext != null) {
            asset.mTrackId = playContext.getTrackId();
            asset.mReqId = playContext.getRequestId();
            asset.mListPos = playContext.getListPos();
            asset.mVideoPos = playContext.getVideoPos();
        }
        if (Log.isLoggable("mdxui", 2)) {
            Log.v("mdxui", "ASSET: created " + asset);
        }
        return asset;
    }
    
    public static Asset createForPostPlay(final Playable playable, final PlayContext playContext, final int mPostPlayVideoPlayed) {
        final Asset create = create(playable, playContext);
        create.mPostPlayVideoPlayed = mPostPlayVideoPlayed;
        return create;
    }
    
    public static Asset fromIntent(final Intent intent) {
        final Asset asset = new Asset();
        asset.mPlayableId = readString(intent, "playableid");
        asset.mParentId = readString(intent, "parentid");
        asset.mIsEpisode = readBoolean(intent, "isEpisode");
        asset.mTrackId = readInt(intent, "trkid");
        asset.mTitle = readString(intent, "title");
        asset.mParentTitle = readString(intent, "parent_title");
        asset.mNflx = readString(intent, "nflx");
        asset.mPlaybackBookmark = readInt(intent, "playback_bookmark");
        asset.mWatchedDate = readLong(intent, "watched_date");
        asset.mSocialDoNotShareVisible = readBoolean(intent, "fb_dontsharebtn");
        asset.mSeasonNumber = readInt(intent, "seasonNumber");
        asset.mEpisodeNumber = readInt(intent, "episodeNumber");
        asset.mDuration = readInt(intent, "duration");
        asset.mEndtime = readInt(intent, "endtime");
        asset.mIsAutoPlayEnabled = readBoolean(intent, "isAutoPlayEnabled");
        asset.mIsNextPlayableEpisode = readBoolean(intent, "isNextPlayableEpisode");
        asset.mReqId = readString(intent, "reqid");
        asset.mListPos = readInt(intent, "listpos");
        asset.mVideoPos = readInt(intent, "videopos");
        asset.mPostPlayVideoPlayed = readInt(intent, "postPlayCount");
        return asset;
    }
    
    private static boolean readBoolean(final Intent intent, final String s) {
        return intent.getBooleanExtra(s, false);
    }
    
    private static boolean readBoolean(final Parcel parcel) {
        return "true".equals(parcel.readString());
    }
    
    private static int readInt(final Intent intent, final String s) {
        return intent.getIntExtra(s, 0);
    }
    
    private static int readInt(final Parcel parcel) {
        return readInt(parcel.readString());
    }
    
    private static int readInt(final String s) {
        if ("=NULL=".equals(s)) {
            return 0;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Throwable t) {
            Log.e("mdxui", "Failed to parse string to int ", t);
            return 0;
        }
    }
    
    private static long readLong(final Intent intent, final String s) {
        return intent.getLongExtra(s, 0L);
    }
    
    private static long readLong(final Parcel parcel) {
        return readLong(parcel.readString());
    }
    
    private static long readLong(final String s) {
        if ("=NULL=".equals(s)) {
            return 0L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (Throwable t) {
            Log.e("mdxui", "Failed to parse string to int ", t);
            return 0L;
        }
    }
    
    private static String readString(final Intent intent, final String s) {
        return readString(intent.getStringExtra(s));
    }
    
    private static String readString(final Parcel parcel) {
        return readString(parcel.readString());
    }
    
    private static String readString(final String s) {
        String s2 = s;
        if ("=NULL=".equals(s)) {
            s2 = null;
        }
        return s2;
    }
    
    private static String validateString(final String s) {
        if (s != null) {
            return s;
        }
        return "=NULL=";
    }
    
    private static void writeBoolean(final Parcel parcel, final boolean b) {
        parcel.writeString(String.valueOf(b));
    }
    
    private static void writeInt(final Parcel parcel, final int n) {
        parcel.writeString(String.valueOf(n));
    }
    
    private static void writeLong(final Parcel parcel, final long n) {
        parcel.writeString(String.valueOf(n));
    }
    
    private static void writeString(final Parcel parcel, final String s) {
        parcel.writeString(validateString(s));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getDuration() {
        return this.mDuration;
    }
    
    public int getEndtime() {
        return this.mEndtime;
    }
    
    public int getEpisodeNumber() {
        return this.mEpisodeNumber;
    }
    
    public int getListPos() {
        return this.mListPos;
    }
    
    public String getNflx() {
        return this.mNflx;
    }
    
    public String getParentId() {
        return this.mParentId;
    }
    
    public String getParentTitle() {
        return this.mParentTitle;
    }
    
    public String getPlayableId() {
        return this.mPlayableId;
    }
    
    public int getPlaybackBookmark() {
        return this.mPlaybackBookmark;
    }
    
    public int getPostPlayVideoPlayed() {
        return this.mPostPlayVideoPlayed;
    }
    
    public String getRequestId() {
        return this.mReqId;
    }
    
    public int getSeasonNumber() {
        return this.mSeasonNumber;
    }
    
    public String getTitle() {
        return this.mTitle;
    }
    
    public int getTrackId() {
        return this.mTrackId;
    }
    
    public int getVideoPos() {
        return this.mVideoPos;
    }
    
    public long getWatchedDate() {
        return this.mWatchedDate;
    }
    
    public boolean isAutoPlayEnabled() {
        return this.mIsAutoPlayEnabled;
    }
    
    public boolean isEmpty() {
        return StringUtils.isEmpty(this.mPlayableId);
    }
    
    public boolean isEpisode() {
        return this.mIsEpisode;
    }
    
    public boolean isNextPlayableEpisode() {
        return this.mIsNextPlayableEpisode;
    }
    
    public boolean isSocialDoNotShareVisible() {
        return this.mSocialDoNotShareVisible;
    }
    
    public void setPlaybackBookmark(final int mPlaybackBookmark) {
        this.mPlaybackBookmark = mPlaybackBookmark;
    }
    
    public Intent toIntent(final Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Intent can not be null!");
        }
        intent.putExtra("playableid", validateString(this.mPlayableId));
        intent.putExtra("parentid", validateString(this.mParentId));
        intent.putExtra("isEpisode", this.mIsEpisode);
        intent.putExtra("trkid", this.mTrackId);
        intent.putExtra("title", validateString(this.mTitle));
        intent.putExtra("parent_title", validateString(this.mParentTitle));
        intent.putExtra("watched_date", this.mWatchedDate);
        intent.putExtra("playback_bookmark", this.mPlaybackBookmark);
        intent.putExtra("nflx", validateString(this.mNflx));
        intent.putExtra("fb_dontsharebtn", this.mSocialDoNotShareVisible);
        intent.putExtra("seasonNumber", this.mSeasonNumber);
        intent.putExtra("episodeNumber", this.mEpisodeNumber);
        intent.putExtra("duration", this.mDuration);
        intent.putExtra("endtime", this.mEndtime);
        intent.putExtra("isAutoPlayEnabled", this.mIsAutoPlayEnabled);
        intent.putExtra("isNextPlayableEpisode", this.mIsNextPlayableEpisode);
        intent.putExtra("reqid", validateString(this.mReqId));
        intent.putExtra("listpos", this.mListPos);
        intent.putExtra("videopos", this.mVideoPos);
        intent.putExtra("postPlayCount", this.mPostPlayVideoPlayed);
        return intent;
    }
    
    @Override
    public String toString() {
        return "Asset [mPlayableId=" + this.mPlayableId + ", mParentId=" + this.mParentId + ", mIsEpisode=" + this.mIsEpisode + ", mIsAutoPlayEnabled=" + this.mIsAutoPlayEnabled + ", mIsNextPlayableEpisode=" + this.mIsNextPlayableEpisode + ", mTrackId=" + this.mTrackId + ", mReqId=" + this.mReqId + ", mListPos=" + this.mListPos + ", mVideoPos=" + this.mVideoPos + ", mTitle=" + this.mTitle + ", mParentTitle=" + this.mParentTitle + ", mWatchedDate=" + this.mWatchedDate + ", mPlaybackBookmark=" + this.mPlaybackBookmark + ", mNflx=" + this.mNflx + ", mDuration=" + this.mDuration + ", mEndtime=" + this.mEndtime + ", mSocialDoNotShareVisible=" + this.mSocialDoNotShareVisible + ", mSeasonNumber=" + this.mSeasonNumber + ", mEpisodeNumber=" + this.mEpisodeNumber + ", mPostPlayVideoPlayed=" + this.mPostPlayVideoPlayed + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        writeString(parcel, this.mPlayableId);
        writeString(parcel, this.mParentId);
        writeInt(parcel, this.mTrackId);
        writeBoolean(parcel, this.mIsEpisode);
        writeString(parcel, this.mTitle);
        writeString(parcel, this.mParentTitle);
        writeLong(parcel, this.mWatchedDate);
        writeInt(parcel, this.mPlaybackBookmark);
        writeString(parcel, this.mNflx);
        writeBoolean(parcel, this.mSocialDoNotShareVisible);
        writeInt(parcel, this.mSeasonNumber);
        writeInt(parcel, this.mEpisodeNumber);
        writeInt(parcel, this.mDuration);
        writeString(parcel, this.mReqId);
        writeInt(parcel, this.mListPos);
        writeInt(parcel, this.mVideoPos);
        writeInt(parcel, this.mEndtime);
        writeBoolean(parcel, this.mIsAutoPlayEnabled);
        writeBoolean(parcel, this.mIsNextPlayableEpisode);
        writeInt(parcel, this.mPostPlayVideoPlayed);
    }
}
