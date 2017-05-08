// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

public enum OfflinePlaybackState
{
    MANIFEST_FETCH("100.1"), 
    MANIFEST_PROCESSING("101.1"), 
    PLAYBACK_INIT("102.1"), 
    PLAYBACK_PLAY("102.2");
    
    String mState;
    
    private OfflinePlaybackState(final String mState) {
        this.mState = mState;
    }
    
    @Override
    public String toString() {
        return this.mState;
    }
}
