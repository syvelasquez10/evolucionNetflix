// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.media.SubtitleUrl;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;

public interface SubtitleParser
{
    int getNumberOfDisplayedSubtitles();
    
    int getNumberOfSubtitlesExpectedToBeDisplayed();
    
    ISubtitleDef$SubtitleProfile getSubtitleProfile();
    
    SubtitleUrl getSubtitleUrl();
    
    SubtitleScreen getSubtitlesForPosition(final long p0);
    
    boolean isReady();
    
    void load();
    
    void seeked(final int p0);
}
