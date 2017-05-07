// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.servicemgr.model.VideoType;

public class NflxProtocolUtils$VideoInfo
{
    public static NflxProtocolUtils$VideoInfo DELAYED;
    private final String mCatalogId;
    private final boolean mHandleWithDelay;
    private final String mShowId;
    private final VideoType mVideoType;
    
    static {
        NflxProtocolUtils$VideoInfo.DELAYED = new NflxProtocolUtils$VideoInfo(true, VideoType.UNAVAILABLE, null);
    }
    
    private NflxProtocolUtils$VideoInfo(final boolean mHandleWithDelay, final VideoType mVideoType, final String mCatalogId) {
        this.mHandleWithDelay = mHandleWithDelay;
        this.mVideoType = mVideoType;
        this.mCatalogId = mCatalogId;
        this.mShowId = null;
    }
    
    private NflxProtocolUtils$VideoInfo(final boolean mHandleWithDelay, final String mCatalogId) {
        this.mHandleWithDelay = mHandleWithDelay;
        this.mVideoType = VideoType.SHOW;
        this.mCatalogId = mCatalogId;
        this.mShowId = null;
    }
    
    private NflxProtocolUtils$VideoInfo(final boolean mHandleWithDelay, final String mShowId, final String mCatalogId) {
        this.mHandleWithDelay = mHandleWithDelay;
        this.mVideoType = VideoType.EPISODE;
        this.mCatalogId = mCatalogId;
        this.mShowId = mShowId;
    }
    
    public static NflxProtocolUtils$VideoInfo createFromEpisode(final String s, final String s2) {
        if (StringUtils.safeEquals(s, s2)) {
            return new NflxProtocolUtils$VideoInfo(false, s);
        }
        return new NflxProtocolUtils$VideoInfo(false, s, s2);
    }
    
    public static NflxProtocolUtils$VideoInfo createFromMovie(final String s) {
        return new NflxProtocolUtils$VideoInfo(false, VideoType.MOVIE, s);
    }
    
    public static NflxProtocolUtils$VideoInfo createFromShow(final String s) {
        return new NflxProtocolUtils$VideoInfo(false, VideoType.SHOW, s);
    }
    
    public String getCatalogId() {
        return this.mCatalogId;
    }
    
    public String getShowId() {
        return this.mShowId;
    }
    
    public VideoType getVideoType() {
        return this.mVideoType;
    }
    
    public boolean handleWithDelay() {
        return this.mHandleWithDelay;
    }
}
