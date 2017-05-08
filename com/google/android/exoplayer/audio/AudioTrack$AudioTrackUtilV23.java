// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.annotation.TargetApi;

@TargetApi(23)
class AudioTrack$AudioTrackUtilV23 extends AudioTrack$AudioTrackUtilV19
{
    private PlaybackParams playbackParams;
    private float playbackSpeed;
    
    public AudioTrack$AudioTrackUtilV23() {
        this.playbackSpeed = 1.0f;
    }
    
    private void maybeApplyPlaybackParams() {
        if (this.audioTrack != null && this.playbackParams != null) {
            this.audioTrack.setPlaybackParams(this.playbackParams);
        }
    }
    
    @Override
    public float getPlaybackSpeed() {
        return this.playbackSpeed;
    }
    
    @Override
    public void reconfigure(final AudioTrack audioTrack, final boolean b) {
        super.reconfigure(audioTrack, b);
        this.maybeApplyPlaybackParams();
    }
    
    @Override
    public void setPlaybackParameters(PlaybackParams allowDefaults) {
        if (allowDefaults == null) {
            allowDefaults = new PlaybackParams();
        }
        allowDefaults = allowDefaults.allowDefaults();
        this.playbackParams = allowDefaults;
        this.playbackSpeed = allowDefaults.getSpeed();
        this.maybeApplyPlaybackParams();
    }
}
