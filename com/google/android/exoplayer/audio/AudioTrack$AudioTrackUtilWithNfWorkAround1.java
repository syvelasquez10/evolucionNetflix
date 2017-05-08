// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.AudioTrack;
import android.annotation.TargetApi;

@TargetApi(19)
class AudioTrack$AudioTrackUtilWithNfWorkAround1 extends AudioTrack$AudioTrackUtil
{
    private int allocateBuffer;
    private boolean isPlaying;
    private int minBuffer;
    private int totalFrameSincePause;
    private boolean wasPaused;
    
    private AudioTrack$AudioTrackUtilWithNfWorkAround1() {
        super(null);
        this.isPlaying = true;
        this.totalFrameSincePause = 0;
        this.minBuffer = 0;
        this.allocateBuffer = 0;
    }
    
    @Override
    int getAvailableSpaceBytes(int n) {
        if (this.wasPaused && !this.isPlaying) {
            n = -1;
        }
        else if (!this.wasPaused || !this.isPlaying || this.totalFrameSincePause >= this.minBuffer + this.allocateBuffer) {
            return 0;
        }
        return n;
    }
    
    @Override
    public void pause() {
        if (this.stopTimestampUs != -1L) {
            return;
        }
        this.wasPaused = true;
        this.isPlaying = false;
        this.totalFrameSincePause = 0;
    }
    
    @Override
    void pcmFrameSubmitted(final int n) {
        this.totalFrameSincePause += n;
    }
    
    @Override
    void play() {
        this.audioTrack.play();
        this.isPlaying = true;
    }
    
    @Override
    public void reconfigure(final AudioTrack audioTrack, final boolean b) {
        super.reconfigure(audioTrack, b);
        this.wasPaused = false;
        this.totalFrameSincePause = 0;
    }
    
    @Override
    void setBufferFrameSize(final int minBuffer, final int allocateBuffer) {
        this.minBuffer = minBuffer;
        this.allocateBuffer = allocateBuffer;
    }
}
