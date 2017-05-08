// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.event.nrdp.media.SubtitleUrl;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public interface SubtitleParser
{
    int getNumberOfDisplayedSubtitles();
    
    int getNumberOfSubtitlesExpectedToBeDisplayed();
    
    IMedia$SubtitleProfile getSubtitleProfile();
    
    SubtitleUrl getSubtitleUrl();
    
    SubtitleScreen getSubtitlesForPosition(final long p0);
    
    boolean isReady();
    
    void load();
    
    void seeked(final int p0);
}
