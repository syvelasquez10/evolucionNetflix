// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

public class KidsCharacter$KidsDetail
{
    public int episodeCount;
    public boolean hasWatchedRecently;
    public int movieCount;
    public String storyImgUrl;
    public String synopsis;
    
    @Override
    public String toString() {
        return "KidsDetail [episodeCount=" + this.episodeCount + ", movieCount=" + this.movieCount + ", hasWatchedRecently=" + this.hasWatchedRecently + ", synopsis=" + this.synopsis + ", storyImgUrl=" + this.storyImgUrl + "]";
    }
}
