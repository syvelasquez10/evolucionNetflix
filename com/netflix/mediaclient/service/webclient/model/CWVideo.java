// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

public class CWVideo extends PlayableVideo implements com.netflix.mediaclient.servicemgr.model.CWVideo
{
    @Override
    public String getInterestingUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.intrUrl;
    }
    
    @Override
    public String getStillUrl() {
        if (this.bookmarkStill == null) {
            return null;
        }
        return this.bookmarkStill.stillUrl;
    }
    
    @Override
    public String toString() {
        return "CWVideo [summary=" + this.summary + ", bookmark=" + this.bookmark + ", bookmarkStill=" + this.bookmarkStill + ", detail=" + this.detail + ", socialEvidence=" + this.socialEvidence + ", currentEpisode=" + this.currentEpisode + ", currentEpisodeBookmark=" + this.currentEpisodeBookmark + ", userConnectedToFacebook=" + this.userConnectedToFacebook + ", inQueue=" + this.inQueue + ", rating=" + this.rating + "]";
    }
}
