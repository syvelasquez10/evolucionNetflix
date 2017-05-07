// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.Video;

public class Video$Summary implements Video
{
    public String boxartUrl;
    public VideoType enumType;
    public VideoType errorType;
    public String horzDispUrl;
    public String id;
    public boolean isEpisode;
    public String squareUrl;
    public String title;
    public String tvCardUrl;
    public String type;
    public int videoYear;
    
    @Override
    public String getBoxshotURL() {
        return this.boxartUrl;
    }
    
    @Override
    public VideoType getErrorType() {
        return this.errorType;
    }
    
    @Override
    public String getHorzDispUrl() {
        return this.horzDispUrl;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getSquareUrl() {
        return this.squareUrl;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public String getTvCardUrl() {
        return this.tvCardUrl;
    }
    
    @Override
    public VideoType getType() {
        if (this.enumType == null) {
            this.enumType = VideoType.create(this.type);
        }
        return this.enumType;
    }
    
    public boolean isEpisode() {
        return this.isEpisode;
    }
    
    public void setErrorType(final VideoType errorType) {
        this.errorType = errorType;
    }
    
    @Override
    public String toString() {
        return "Summary [id=" + this.id + ", boxartUrl=" + this.boxartUrl + ", type=" + this.type + ", enumType=" + this.enumType + ", title=" + this.title + ", isEpisode=" + this.isEpisode + ", errorType=" + this.errorType + ", tvCardUrl=" + this.tvCardUrl + ", horzDispUrl=" + this.horzDispUrl + ", squareUrl=" + this.squareUrl + ", videoYear=" + this.videoYear + "]";
    }
}
