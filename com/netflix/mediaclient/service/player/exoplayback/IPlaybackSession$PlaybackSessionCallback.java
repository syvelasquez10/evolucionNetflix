// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;

public interface IPlaybackSession$PlaybackSessionCallback
{
    void handleError(final ExoPlaybackError p0);
    
    void handlePlaying();
    
    void handlePrepared();
    
    void handleStarted();
    
    void handleStopped();
    
    void handleSubtitleUpdate(final SubtitleScreen p0);
    
    void handleUpdatePts(final int p0);
}
