// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

import com.netflix.mediaclient.servicemgr.Asset;

public class PlaybackBookmark
{
    public final int mBookmarkInSecond;
    public final long mBookmarkUpdateTimeInUTCMs;
    public final String mVideoId;
    
    public PlaybackBookmark(final int mBookmarkInSecond, final long mBookmarkUpdateTimeInUTCMs, final String mVideoId) {
        this.mBookmarkInSecond = mBookmarkInSecond;
        this.mBookmarkUpdateTimeInUTCMs = mBookmarkUpdateTimeInUTCMs;
        this.mVideoId = mVideoId;
    }
    
    public static PlaybackBookmark fromAsset(final Asset asset) {
        return new PlaybackBookmark(asset.getPlaybackBookmark(), System.currentTimeMillis(), asset.getPlayableId());
    }
}
