// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Bundle;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.ParcelUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.os.Parcelable;

public class Asset implements Parcelable, PlayContext
{
    public static final Parcelable$Creator<Asset> CREATOR;
    public static final String PARAM_AUTOPLAY_MAX_COUNT = "autoPlayMaxCount";
    public static final String PARAM_DURATION = "duration";
    public static final String PARAM_ENDTIME = "endtime";
    public static final String PARAM_EP_BOOKMARK = "playback_bookmark";
    public static final String PARAM_EP_BOOKMARK_TS = "watched_date";
    public static final String PARAM_EP_EPISODE_NUMBER = "episodeNumber";
    public static final String PARAM_EP_SEASON_NUMBER = "seasonNumber";
    public static final String PARAM_EXPIRATION_TIME = "expirationTime";
    public static final String PARAM_IS_ADVISORY_DISABLED = "isAdvisoryDisabled";
    public static final String PARAM_IS_AGE_PROTECTED = "isAgeProtected";
    public static final String PARAM_IS_AUTOPLAY = "isAutoPlayEnabled";
    public static final String PARAM_IS_BROWSE_PLAY = "isBrowsePlay";
    public static final String PARAM_IS_EPISODE = "isEpisode";
    public static final String PARAM_IS_EXEMPT_INTERRUPTER_LIMIT = "isExemptFromInterrupterLimit";
    public static final String PARAM_IS_NEXT_EPISODE = "isNextPlayableEpisode";
    public static final String PARAM_IS_NSRE = "isNSRE";
    public static final String PARAM_IS_PIN_PROTECTED = "isPinProtected";
    public static final String PARAM_IS_PIN_VERIFIED = "isPinVerified";
    public static final String PARAM_IS_PREVIEW_PROTECTED = "isPreviewProtected";
    public static final String PARAM_IS_SUPPLEMENTAL_VIDEO = "isSupplementalVideo";
    public static final String PARAM_LIST_POS = "listpos";
    public static final String PARAM_LOGICAL_START = "logicalStart";
    public static final String PARAM_OFFLINE_AVAILABLE = "offlineAvailable";
    public static final String PARAM_ORIGINAL_URL = "nflx";
    public static final String PARAM_PARENT_ID = "parentid";
    public static final String PARAM_PARENT_TITLE = "parent_title";
    public static final String PARAM_PLAYABLE_ID = "playableid";
    public static final String PARAM_PLAY_REQUEST_LOCATION = "playLocation";
    public static final String PARAM_POSTPLAY_COUNT = "postPlayCount";
    public static final String PARAM_REQ_ID = "reqid";
    public static final String PARAM_SEASON_NUM_ABBR_LABEL = "seasonNumAbbrLabel";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_TRK_ID = "trkid";
    public static final String PARAM_VIDEO_POS = "videopos";
    private static final String TAG = "nf_asset";
    private int mAutoPlayMaxCount;
    private int mDuration;
    private int mEndtime;
    private int mEpisodeNumber;
    private long mExpirationTime;
    private boolean mIsAdvisoryDisabled;
    private boolean mIsAgeProtected;
    private boolean mIsAutoPlayEnabled;
    private boolean mIsAvailableOffline;
    private boolean mIsBrowsePlay;
    private boolean mIsEpisode;
    private boolean mIsExemptFromInterrupterLimit;
    private boolean mIsNSRE;
    private boolean mIsNextPlayableEpisode;
    private boolean mIsPinProtected;
    private boolean mIsPinVerified;
    private boolean mIsPreviewProtected;
    private boolean mIsSupplementalVideo;
    private int mListPos;
    private int mLogicalStart;
    private String mNflx;
    private String mParentId;
    private String mParentTitle;
    private String mPlayLocation;
    private String mPlayableId;
    private int mPlaybackBookmark;
    private int mPostPlayVideoPlayed;
    private String mReqId;
    private String mSeasonAbbrSeqLabel;
    private int mSeasonNumber;
    private String mTitle;
    private int mTrackId;
    private int mVideoPos;
    private long mWatchedDate;
    
    static {
        CREATOR = (Parcelable$Creator)new Asset$1();
    }
    
    private Asset() {
        this.mAutoPlayMaxCount = -1;
    }
    
    private Asset(final Parcel parcel) {
        this.mAutoPlayMaxCount = -1;
        this.mPlayableId = ParcelUtils.readString(parcel);
        this.mParentId = ParcelUtils.readString(parcel);
        this.mTrackId = ParcelUtils.readInt(parcel);
        this.mIsNSRE = ParcelUtils.readBoolean(parcel);
        this.mIsEpisode = ParcelUtils.readBoolean(parcel);
        this.mIsSupplementalVideo = ParcelUtils.readBoolean(parcel);
        this.mTitle = ParcelUtils.readString(parcel);
        this.mParentTitle = ParcelUtils.readString(parcel);
        this.mWatchedDate = ParcelUtils.readLong(parcel);
        this.mPlaybackBookmark = ParcelUtils.readInt(parcel);
        this.mNflx = ParcelUtils.readString(parcel);
        this.mSeasonNumber = ParcelUtils.readInt(parcel);
        this.mEpisodeNumber = ParcelUtils.readInt(parcel);
        this.mDuration = ParcelUtils.readInt(parcel);
        this.mReqId = ParcelUtils.readString(parcel);
        this.mListPos = ParcelUtils.readInt(parcel);
        this.mVideoPos = ParcelUtils.readInt(parcel);
        this.mEndtime = ParcelUtils.readInt(parcel);
        this.mLogicalStart = ParcelUtils.readInt(parcel);
        this.mIsAutoPlayEnabled = ParcelUtils.readBoolean(parcel);
        this.mIsNextPlayableEpisode = ParcelUtils.readBoolean(parcel);
        this.mPostPlayVideoPlayed = ParcelUtils.readInt(parcel);
        this.mIsAgeProtected = ParcelUtils.readBoolean(parcel);
        this.mIsPinProtected = ParcelUtils.readBoolean(parcel);
        this.mIsPreviewProtected = ParcelUtils.readBoolean(parcel);
        this.mIsPinVerified = ParcelUtils.readBoolean(parcel);
        this.mExpirationTime = ParcelUtils.readLong(parcel);
        this.mPlayLocation = ParcelUtils.readString(parcel);
        this.mIsAdvisoryDisabled = ParcelUtils.readBoolean(parcel);
        this.mIsBrowsePlay = ParcelUtils.readBoolean(parcel);
        this.mSeasonAbbrSeqLabel = ParcelUtils.readString(parcel);
        this.mAutoPlayMaxCount = ParcelUtils.readInt(parcel);
        this.mIsExemptFromInterrupterLimit = ParcelUtils.readBoolean(parcel);
        this.mIsAvailableOffline = ParcelUtils.readBoolean(parcel);
    }
    
    public static Asset create(final Playable playable, final PlayContext playContext, final boolean mIsPinVerified) {
        if (Log.isLoggable()) {
            Log.v("nf_asset", "ASSET: create asset from playable " + playable);
        }
        final Asset asset = new Asset();
        if (playable != null) {
            asset.mPlayableId = playable.getPlayableId();
            asset.mParentId = playable.getTopLevelId();
            asset.mTitle = playable.getPlayableTitle();
            asset.mParentTitle = playable.getParentTitle();
            asset.mPlaybackBookmark = playable.getPlayableBookmarkPosition();
            asset.mWatchedDate = playable.getPlayableBookmarkUpdateTime();
            asset.mDuration = playable.getRuntime();
            asset.mEndtime = playable.getEndtime();
            asset.mLogicalStart = playable.getLogicalStart();
            asset.mSeasonNumber = playable.getSeasonNumber();
            asset.mEpisodeNumber = playable.getEpisodeNumber();
            asset.mIsNSRE = playable.isNSRE();
            asset.mIsEpisode = playable.isPlayableEpisode();
            asset.mIsSupplementalVideo = playable.isSupplementalVideo();
            asset.mIsAutoPlayEnabled = playable.isAutoPlayEnabled();
            asset.mIsExemptFromInterrupterLimit = playable.isExemptFromInterrupterLimit();
            asset.mAutoPlayMaxCount = playable.getAutoPlayMaxCount();
            asset.mIsNextPlayableEpisode = playable.isNextPlayableEpisode();
            asset.mIsAgeProtected = playable.isAgeProtected();
            asset.mIsPinProtected = playable.isPinProtected();
            asset.mIsPreviewProtected = playable.isPreviewProtected();
            asset.mExpirationTime = playable.getExpirationTime();
            asset.mIsAdvisoryDisabled = playable.isAdvisoryDisabled();
            asset.mSeasonAbbrSeqLabel = playable.getSeasonAbbrSeqLabel();
            asset.mIsAvailableOffline = playable.isAvailableOffline();
        }
        if (playContext != null) {
            asset.mTrackId = playContext.getTrackId();
            asset.mReqId = playContext.getRequestId();
            asset.mListPos = playContext.getListPos();
            asset.mVideoPos = playContext.getVideoPos();
            asset.mPlayLocation = playContext.getPlayLocation().getValue();
            asset.mIsBrowsePlay = playContext.getBrowsePlay();
        }
        if (Log.isLoggable()) {
            Log.v("nf_asset", "ASSET: created " + asset);
        }
        asset.mIsPinVerified = mIsPinVerified;
        return asset;
    }
    
    public static Asset createForPostPlay(final Playable playable, final PlayContext playContext, final int mPostPlayVideoPlayed, final boolean b) {
        final Asset create = create(playable, playContext, b);
        create.mPostPlayVideoPlayed = mPostPlayVideoPlayed;
        if (create.isEpisode()) {
            create.mPlaybackBookmark = create.mLogicalStart;
            Log.d("nf_asset", String.format("%s postPlay start set to logicalPos: %d", create.mPlayableId, create.mLogicalStart));
        }
        return create;
    }
    
    public static Asset fromIntent(final Intent intent) {
        final Asset asset = new Asset();
        asset.mPlayableId = ParcelUtils.readString(intent, "playableid");
        asset.mParentId = ParcelUtils.readString(intent, "parentid");
        asset.mIsNSRE = ParcelUtils.readBoolean(intent, "isNSRE");
        asset.mIsEpisode = ParcelUtils.readBoolean(intent, "isEpisode");
        asset.mIsSupplementalVideo = ParcelUtils.readBoolean(intent, "isSupplementalVideo");
        asset.mTrackId = ParcelUtils.readInt(intent, "trkid");
        asset.mTitle = ParcelUtils.readString(intent, "title");
        asset.mParentTitle = ParcelUtils.readString(intent, "parent_title");
        asset.mNflx = ParcelUtils.readString(intent, "nflx");
        asset.mPlaybackBookmark = ParcelUtils.readInt(intent, "playback_bookmark");
        asset.mWatchedDate = ParcelUtils.readLong(intent, "watched_date");
        asset.mSeasonNumber = ParcelUtils.readInt(intent, "seasonNumber");
        asset.mEpisodeNumber = ParcelUtils.readInt(intent, "episodeNumber");
        asset.mDuration = ParcelUtils.readInt(intent, "duration");
        asset.mEndtime = ParcelUtils.readInt(intent, "endtime");
        asset.mLogicalStart = ParcelUtils.readInt(intent, "logicalStart");
        asset.mIsAutoPlayEnabled = ParcelUtils.readBoolean(intent, "isAutoPlayEnabled");
        asset.mIsExemptFromInterrupterLimit = ParcelUtils.readBoolean(intent, "isExemptFromInterrupterLimit");
        asset.mAutoPlayMaxCount = ParcelUtils.readInt(intent, "autoPlayMaxCount");
        asset.mIsNextPlayableEpisode = ParcelUtils.readBoolean(intent, "isNextPlayableEpisode");
        asset.mReqId = ParcelUtils.readString(intent, "reqid");
        asset.mListPos = ParcelUtils.readInt(intent, "listpos");
        asset.mVideoPos = ParcelUtils.readInt(intent, "videopos");
        asset.mPostPlayVideoPlayed = ParcelUtils.readInt(intent, "postPlayCount");
        asset.mIsAgeProtected = ParcelUtils.readBoolean(intent, "isAgeProtected");
        asset.mIsPinProtected = ParcelUtils.readBoolean(intent, "isPinProtected");
        asset.mIsPreviewProtected = ParcelUtils.readBoolean(intent, "isPreviewProtected");
        asset.mIsPinVerified = ParcelUtils.readBoolean(intent, "isPinVerified");
        asset.mExpirationTime = ParcelUtils.readLong(intent, "expirationTime");
        asset.mPlayLocation = ParcelUtils.readString(intent, "playLocation");
        asset.mIsAdvisoryDisabled = ParcelUtils.readBoolean(intent, "isAdvisoryDisabled");
        asset.mIsBrowsePlay = ParcelUtils.readBoolean(intent, "isBrowsePlay");
        asset.mSeasonAbbrSeqLabel = ParcelUtils.readString(intent, "seasonNumAbbrLabel");
        return asset;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getAutoPlayMaxCount() {
        return this.mAutoPlayMaxCount;
    }
    
    public boolean getBrowsePlay() {
        return this.mIsBrowsePlay;
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
    
    public long getExpirationTime() {
        return this.mExpirationTime;
    }
    
    public int getHeroTrackId() {
        throw new UnsupportedOperationException("Should not be needed");
    }
    
    public int getListPos() {
        return this.mListPos;
    }
    
    public int getLogicalStart() {
        return this.mLogicalStart;
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
    
    public PlayLocationType getPlayLocation() {
        return PlayLocationType.create(this.mPlayLocation);
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
    
    public String getSeasonAbbrSeqLabel() {
        return this.mSeasonAbbrSeqLabel;
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
    
    public boolean isAdvisoryDisabled() {
        return this.mIsAdvisoryDisabled;
    }
    
    public boolean isAgeProtected() {
        return this.mIsAgeProtected;
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
    
    public boolean isExemptFromInterrupterLimit() {
        return this.mIsExemptFromInterrupterLimit;
    }
    
    public boolean isHero() {
        return false;
    }
    
    public boolean isNSRE() {
        return this.mIsNSRE;
    }
    
    public boolean isNextPlayableEpisode() {
        return this.mIsNextPlayableEpisode;
    }
    
    public boolean isPinProtected() {
        return this.mIsPinProtected;
    }
    
    public boolean isPinVerified() {
        return this.mIsPinVerified;
    }
    
    public boolean isPreviewProtected() {
        return this.mIsPreviewProtected;
    }
    
    public boolean isSupplementalVideo() {
        return this.mIsSupplementalVideo;
    }
    
    public Bundle playContextToBundle() {
        return PlayContextImp.playContextToBundle(this);
    }
    
    public void setAdvisoryDisabled(final boolean mIsAdvisoryDisabled) {
        this.mIsAdvisoryDisabled = mIsAdvisoryDisabled;
    }
    
    public void setBrowsePlay(final boolean mIsBrowsePlay) {
        this.mIsBrowsePlay = mIsBrowsePlay;
    }
    
    public void setPinVerified(final boolean mIsPinVerified) {
        this.mIsPinVerified = mIsPinVerified;
    }
    
    public void setPlayLocation(final PlayLocationType playLocationType) {
        this.mPlayLocation = playLocationType.getValue();
    }
    
    public void setPlaybackBookmark(final int mPlaybackBookmark) {
        this.mPlaybackBookmark = mPlaybackBookmark;
    }
    
    public void setPreviewProtected(final boolean mIsPreviewProtected) {
        this.mIsPreviewProtected = mIsPreviewProtected;
    }
    
    public Intent toIntent(final Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Intent can not be null!");
        }
        intent.putExtra("playableid", ParcelUtils.validateString(this.mPlayableId));
        intent.putExtra("parentid", ParcelUtils.validateString(this.mParentId));
        intent.putExtra("isNSRE", this.mIsNSRE);
        intent.putExtra("isEpisode", this.mIsEpisode);
        intent.putExtra("isSupplementalVideo", this.mIsSupplementalVideo);
        intent.putExtra("trkid", this.mTrackId);
        intent.putExtra("title", ParcelUtils.validateString(this.mTitle));
        intent.putExtra("parent_title", ParcelUtils.validateString(this.mParentTitle));
        intent.putExtra("watched_date", this.mWatchedDate);
        intent.putExtra("playback_bookmark", this.mPlaybackBookmark);
        intent.putExtra("nflx", ParcelUtils.validateString(this.mNflx));
        intent.putExtra("seasonNumber", this.mSeasonNumber);
        intent.putExtra("episodeNumber", this.mEpisodeNumber);
        intent.putExtra("duration", this.mDuration);
        intent.putExtra("endtime", this.mEndtime);
        intent.putExtra("logicalStart", this.mLogicalStart);
        intent.putExtra("isAutoPlayEnabled", this.mIsAutoPlayEnabled);
        intent.putExtra("isExemptFromInterrupterLimit", this.mIsExemptFromInterrupterLimit);
        intent.putExtra("autoPlayMaxCount", this.mAutoPlayMaxCount);
        intent.putExtra("isNextPlayableEpisode", this.mIsNextPlayableEpisode);
        intent.putExtra("reqid", ParcelUtils.validateString(this.mReqId));
        intent.putExtra("listpos", this.mListPos);
        intent.putExtra("videopos", this.mVideoPos);
        intent.putExtra("postPlayCount", this.mPostPlayVideoPlayed);
        intent.putExtra("isAgeProtected", this.mIsAgeProtected);
        intent.putExtra("isPinProtected", this.mIsPinProtected);
        intent.putExtra("isPreviewProtected", this.mIsPreviewProtected);
        intent.putExtra("isPinVerified", this.mIsPinVerified);
        intent.putExtra("expirationTime", this.mExpirationTime);
        intent.putExtra("playLocation", this.mPlayLocation);
        intent.putExtra("isAdvisoryDisabled", this.mIsAdvisoryDisabled);
        intent.putExtra("isBrowsePlay", this.mIsBrowsePlay);
        intent.putExtra("seasonNumAbbrLabel", this.mSeasonAbbrSeqLabel);
        intent.putExtra("offlineAvailable", this.mIsAvailableOffline);
        return intent;
    }
    
    @Override
    public String toString() {
        return "Asset [mPlayableId=" + this.mPlayableId + ", mParentId=" + this.mParentId + ", mIsNSRE" + this.mIsNSRE + ", mIsEpisode=" + this.mIsEpisode + ", mIsSupplementalVideo=" + this.mIsSupplementalVideo + ", mIsAutoPlayEnabled=" + this.mIsAutoPlayEnabled + ", mIsExemptFromInterrupterLimit=" + this.mIsExemptFromInterrupterLimit + ", mAutoPlayMaxCount=" + this.mAutoPlayMaxCount + ", mIsNextPlayableEpisode=" + this.mIsNextPlayableEpisode + ", mTrackId=" + this.mTrackId + ", mReqId=" + this.mReqId + ", mListPos=" + this.mListPos + ", mVideoPos=" + this.mVideoPos + ", mTitle=" + this.mTitle + ", mParentTitle=" + this.mParentTitle + ", mWatchedDate=" + this.mWatchedDate + ", mPlaybackBookmark=" + this.mPlaybackBookmark + ", mNflx=" + this.mNflx + ", mDuration=" + this.mDuration + ", mEndtime=" + this.mEndtime + ", mLogicalStart=" + this.mLogicalStart + ", mIsAgeProtected=" + this.mIsAgeProtected + ", mIsPinProtected=" + this.mIsPinProtected + ", mIsPreviewProtected=" + this.mIsPreviewProtected + ", mIsPinVerified=" + this.mIsPinVerified + ", mSeasonNumber=" + this.mSeasonNumber + ", mEpisodeNumber=" + this.mEpisodeNumber + ", mPostPlayVideoPlayed=" + this.mPostPlayVideoPlayed + ", mExpirationEnd=" + this.mExpirationTime + ", mPlayRequestedLocation=" + this.mPlayLocation + ", mIsAdvisoryDisabled=" + this.mIsAdvisoryDisabled + ", mIsBrowsePlay=" + this.mIsBrowsePlay + ", mSeasonAbbrSeqLabel=" + this.mSeasonAbbrSeqLabel + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ParcelUtils.writeString(parcel, this.mPlayableId);
        ParcelUtils.writeString(parcel, this.mParentId);
        ParcelUtils.writeInt(parcel, this.mTrackId);
        ParcelUtils.writeBoolean(parcel, this.mIsNSRE);
        ParcelUtils.writeBoolean(parcel, this.mIsEpisode);
        ParcelUtils.writeBoolean(parcel, this.mIsSupplementalVideo);
        ParcelUtils.writeString(parcel, this.mTitle);
        ParcelUtils.writeString(parcel, this.mParentTitle);
        ParcelUtils.writeLong(parcel, this.mWatchedDate);
        ParcelUtils.writeInt(parcel, this.mPlaybackBookmark);
        ParcelUtils.writeString(parcel, this.mNflx);
        ParcelUtils.writeInt(parcel, this.mSeasonNumber);
        ParcelUtils.writeInt(parcel, this.mEpisodeNumber);
        ParcelUtils.writeInt(parcel, this.mDuration);
        ParcelUtils.writeString(parcel, this.mReqId);
        ParcelUtils.writeInt(parcel, this.mListPos);
        ParcelUtils.writeInt(parcel, this.mVideoPos);
        ParcelUtils.writeInt(parcel, this.mEndtime);
        ParcelUtils.writeInt(parcel, this.mLogicalStart);
        ParcelUtils.writeBoolean(parcel, this.mIsAutoPlayEnabled);
        ParcelUtils.writeBoolean(parcel, this.mIsNextPlayableEpisode);
        ParcelUtils.writeInt(parcel, this.mPostPlayVideoPlayed);
        ParcelUtils.writeBoolean(parcel, this.mIsAgeProtected);
        ParcelUtils.writeBoolean(parcel, this.mIsPinProtected);
        ParcelUtils.writeBoolean(parcel, this.mIsPreviewProtected);
        ParcelUtils.writeBoolean(parcel, this.mIsPinVerified);
        ParcelUtils.writeLong(parcel, this.mExpirationTime);
        ParcelUtils.writeString(parcel, this.mPlayLocation);
        ParcelUtils.writeBoolean(parcel, this.mIsAdvisoryDisabled);
        ParcelUtils.writeBoolean(parcel, this.mIsBrowsePlay);
        ParcelUtils.writeString(parcel, this.mSeasonAbbrSeqLabel);
        ParcelUtils.writeInt(parcel, this.mAutoPlayMaxCount);
        ParcelUtils.writeBoolean(parcel, this.mIsExemptFromInterrupterLimit);
        ParcelUtils.writeBoolean(parcel, this.mIsAvailableOffline);
    }
}
