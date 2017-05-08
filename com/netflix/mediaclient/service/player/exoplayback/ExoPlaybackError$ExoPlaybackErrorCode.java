// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

public enum ExoPlaybackError$ExoPlaybackErrorCode
{
    AUDIOTRACK_INIT_ERROR(3), 
    AUDIOTRACK_WRITE_ERROR(4), 
    AUDIO_LOAD_ERROR(1), 
    CRYPTO_ERROR(6), 
    DECODER_INIT_ERROR(5), 
    MANIFEST_FAILURE(9), 
    MPD_ERROR(10), 
    NO_ERROR(0), 
    PLAYER_ERROR(7), 
    SESSION_INIT_ERROR(8), 
    VIDEO_LOAD_ERROR(2);
    
    private final int value;
    
    private ExoPlaybackError$ExoPlaybackErrorCode(final int value) {
        this.value = value;
    }
    
    int getValue() {
        return this.value;
    }
}
