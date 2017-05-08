// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

public final class AudioTrack$InitializationException extends Exception
{
    public final int audioTrackState;
    
    public AudioTrack$InitializationException(final int audioTrackState, final int n, final int n2, final int n3) {
        super("AudioTrack init failed: " + audioTrackState + ", Config(" + n + ", " + n2 + ", " + n3 + ")");
        this.audioTrackState = audioTrackState;
    }
}
