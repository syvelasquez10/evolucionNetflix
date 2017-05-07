// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;

public abstract class Video implements com.netflix.mediaclient.servicemgr.model.Video
{
    public TrackableListSummary similarListSummary;
    public List<com.netflix.mediaclient.servicemgr.model.Video> similarVideos;
    public Video$Summary summary;
    
    public static boolean isSocialVideoType(final VideoType videoType) {
        return VideoType.SOCIAL_FRIEND.equals(videoType) || VideoType.SOCIAL_GROUP.equals(videoType) || VideoType.SOCIAL_POPULAR.equals(videoType);
    }
    
    @Override
    public String getBoxshotUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxshotUrl();
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getErrorType();
    }
    
    @Override
    public String getHorzDispUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getHorzDispUrl();
    }
    
    @Override
    public String getId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public String getSquareUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getSquareUrl();
    }
    
    @Override
    public String getTitle() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTvCardUrl();
    }
    
    @Override
    public VideoType getType() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getType();
    }
}
