// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.audio.AudioTrack$WriteException;
import com.google.android.exoplayer.audio.AudioTrack$InitializationException;

public interface MediaCodecAudioTrackRenderer$EventListener extends MediaCodecTrackRenderer$EventListener
{
    void onAudioTrackInitializationError(final AudioTrack$InitializationException p0);
    
    void onAudioTrackUnderrun(final int p0, final long p1, final long p2);
    
    void onAudioTrackWriteError(final AudioTrack$WriteException p0);
}
