// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public interface ExoPlayer
{
    void addListener(final ExoPlayer$Listener p0);
    
    int getBufferedPercentage();
    
    long getBufferedPosition();
    
    long getCurrentPosition();
    
    long getDuration();
    
    boolean getPlayWhenReady();
    
    int getPlaybackState();
    
    int getSelectedTrack(final int p0);
    
    int getTrackCount(final int p0);
    
    MediaFormat getTrackFormat(final int p0, final int p1);
    
    void prepare(final TrackRenderer... p0);
    
    void release();
    
    void seekTo(final long p0);
    
    void sendMessage(final ExoPlayer$ExoPlayerComponent p0, final int p1, final Object p2);
    
    void setPlayWhenReady(final boolean p0);
    
    void setSelectedTrack(final int p0, final int p1);
    
    void stop();
}
