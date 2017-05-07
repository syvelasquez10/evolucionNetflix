// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.servicemgr.model.Playable;

class TitlePlayable implements Playable
{
    private boolean mCanBeSharedOnFacebook;
    private int mDuration;
    private int mEndtime;
    private boolean mEpisode;
    private int mEpisodeNumber;
    private boolean mIsAutoPlayEnabled;
    private boolean mIsNextPlayableEpisode;
    private boolean mIsPinProtected;
    private int mLogicalStart;
    private String mParentId;
    private String mParentTitle;
    private String mPlayableId;
    private int mPlaybackBookmark;
    private int mSeasonNumber;
    private String mTitle;
    private long mWatchedDate;
    
    TitlePlayable(final Asset asset) {
        this.mPlayableId = asset.getPlayableId();
        this.mParentId = asset.getParentId();
        this.mEpisode = asset.isEpisode();
        this.mTitle = asset.getTitle();
        this.mParentTitle = asset.getParentTitle();
        this.mWatchedDate = asset.getWatchedDate();
        this.mPlaybackBookmark = asset.getPlaybackBookmark();
        this.mCanBeSharedOnFacebook = asset.canBeSharedOnFacebook();
        this.mSeasonNumber = asset.getSeasonNumber();
        this.mEpisodeNumber = asset.getEpisodeNumber();
        this.mDuration = asset.getDuration();
        this.mIsAutoPlayEnabled = asset.isAutoPlayEnabled();
        this.mIsNextPlayableEpisode = asset.isNextPlayableEpisode();
        this.mIsPinProtected = asset.isPinProtected();
    }
    
    @Override
    public boolean canBeSharedOnFacebook() {
        return this.mCanBeSharedOnFacebook;
    }
    
    @Override
    public int getEndtime() {
        return this.mEndtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        return this.mEpisodeNumber;
    }
    
    @Override
    public int getLogicalStart() {
        return this.mLogicalStart;
    }
    
    @Override
    public String getParentId() {
        return this.mParentId;
    }
    
    @Override
    public String getParentTitle() {
        return this.mParentTitle;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        return this.mPlaybackBookmark;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return this.mWatchedDate;
    }
    
    @Override
    public String getPlayableId() {
        return this.mPlayableId;
    }
    
    @Override
    public String getPlayableTitle() {
        return this.mTitle;
    }
    
    @Override
    public int getRuntime() {
        return this.mDuration;
    }
    
    @Override
    public int getSeasonNumber() {
        return this.mSeasonNumber;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.mIsAutoPlayEnabled;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.mIsNextPlayableEpisode;
    }
    
    @Override
    public boolean isPinProtected() {
        return this.mIsPinProtected;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.mEpisode;
    }
}
