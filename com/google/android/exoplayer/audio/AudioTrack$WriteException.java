// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

public final class AudioTrack$WriteException extends Exception
{
    public final int errorCode;
    
    public AudioTrack$WriteException(final int errorCode) {
        super("AudioTrack write failed: " + errorCode);
        this.errorCode = errorCode;
    }
}
