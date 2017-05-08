// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongNextEpisode
{
    KongInteractivePostPlayModel$KongVOSound audio;
    boolean focused;
    KongInteractivePostPlayModel$KongNextEpisodeImage images;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    String title;
    int videoId;
    
    public KongInteractivePostPlayModel$KongNextEpisode(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongVOSound getAudio() {
        return this.audio;
    }
    
    public KongInteractivePostPlayModel$KongNextEpisodeImage getImages() {
        return this.images;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getVideoId() {
        return this.videoId;
    }
    
    public boolean isFocused() {
        return this.focused;
    }
    
    @Override
    public String toString() {
        return "KongNextEpisode{videoId='" + this.videoId + '\'' + ", title='" + this.title + '\'' + ", focused=" + this.focused + ", images=" + this.images + ", audio=" + this.audio + '}';
    }
}
