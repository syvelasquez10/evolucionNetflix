// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public interface ExoPlayer$Listener
{
    void onPlayWhenReadyCommitted();
    
    void onPlayerError(final ExoPlaybackException p0);
    
    void onPlayerStateChanged(final boolean p0, final int p1);
}
