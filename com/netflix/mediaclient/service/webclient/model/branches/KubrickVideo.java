// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

public class KubrickVideo extends Video implements com.netflix.mediaclient.servicemgr.model.KubrickVideo
{
    public Video$KubrickSummary kubrick;
    
    @Override
    public String getCertification() {
        if (this.kubrick != null) {
            return this.kubrick.certification;
        }
        return null;
    }
    
    @Override
    public String getKubrickHorzImgUrl() {
        if (this.kubrick != null) {
            return this.kubrick.horzDispUrl;
        }
        return null;
    }
    
    @Override
    public String getKubrickStoryImgUrl() {
        if (this.kubrick != null) {
            return this.kubrick.storyImgUrl;
        }
        return null;
    }
    
    @Override
    public String getNarrative() {
        if (this.kubrick != null) {
            return this.kubrick.narrative;
        }
        return null;
    }
    
    @Override
    public float getPredictedRating() {
        Float value;
        if (this.kubrick != null) {
            value = this.kubrick.predictedRating;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public int getRuntime() {
        Integer value;
        if (this.kubrick != null) {
            value = this.kubrick.runtime;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public int getSeasonCount() {
        Integer value;
        if (this.kubrick != null) {
            value = this.kubrick.seasonCount;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public String getTitleImgUrl() {
        if (this.kubrick == null) {
            return null;
        }
        return this.kubrick.titleUrl;
    }
    
    @Override
    public float getUserRating() {
        return 0.0f;
    }
    
    @Override
    public int getYear() {
        Integer value;
        if (this.kubrick != null) {
            value = this.kubrick.year;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public boolean isVideoHd() {
        Boolean value;
        if (this.kubrick != null) {
            value = this.kubrick.isHd;
        }
        else {
            value = null;
        }
        return value;
    }
    
    @Override
    public void setUserRating(final float n) {
    }
}
