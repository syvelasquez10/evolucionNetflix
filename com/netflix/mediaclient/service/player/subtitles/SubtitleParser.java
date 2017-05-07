// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;

public interface SubtitleParser
{
    IMedia$SubtitleProfile getSubtitleProfile();
    
    SubtitleScreen getSubtitlesForPosition(final long p0);
    
    boolean isReady();
    
    void load();
    
    void seeked(final int p0);
}
