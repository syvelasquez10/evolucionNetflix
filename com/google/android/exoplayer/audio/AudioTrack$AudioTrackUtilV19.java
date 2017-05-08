// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.AudioTrack;
import android.media.AudioTimestamp;
import android.annotation.TargetApi;

@TargetApi(19)
class AudioTrack$AudioTrackUtilV19 extends AudioTrack$AudioTrackUtil
{
    private final AudioTimestamp audioTimestamp;
    private long lastRawTimestampFramePosition;
    private long lastTimestampFramePosition;
    private long rawTimestampFramePositionWrapCount;
    
    public AudioTrack$AudioTrackUtilV19() {
        super(null);
        this.audioTimestamp = new AudioTimestamp();
    }
    
    @Override
    public long getTimestampFramePosition() {
        return this.lastTimestampFramePosition;
    }
    
    @Override
    public long getTimestampNanoTime() {
        return this.audioTimestamp.nanoTime;
    }
    
    @Override
    public void reconfigure(final AudioTrack audioTrack, final boolean b) {
        super.reconfigure(audioTrack, b);
        this.rawTimestampFramePositionWrapCount = 0L;
        this.lastRawTimestampFramePosition = 0L;
        this.lastTimestampFramePosition = 0L;
    }
    
    @Override
    public boolean updateTimestamp() {
        final boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
        if (timestamp) {
            final long framePosition = this.audioTimestamp.framePosition;
            if (this.lastRawTimestampFramePosition > framePosition) {
                ++this.rawTimestampFramePositionWrapCount;
            }
            this.lastRawTimestampFramePosition = framePosition;
            this.lastTimestampFramePosition = framePosition + (this.rawTimestampFramePositionWrapCount << 32);
        }
        return timestamp;
    }
}
