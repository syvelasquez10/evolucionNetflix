// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

import android.media.PlaybackParams;
import android.os.SystemClock;
import android.os.Build$VERSION;
import android.media.AudioTrack;

class AudioTrack$AudioTrackUtil
{
    protected AudioTrack audioTrack;
    private long endPlaybackHeadPosition;
    private long frameCountBeforeWrapAround;
    private long lastRawPlaybackHeadPosition;
    private boolean needsPassthroughWorkaround;
    private long passthroughWorkaroundPauseOffset;
    private long rawPlaybackHeadWrapCount;
    private int sampleRate;
    private long stopPlaybackHeadPosition;
    protected long stopTimestampUs;
    
    static boolean needPlaybackHeadPositionWorkaround() {
        return Build$VERSION.SDK_INT < 21;
    }
    
    int getAvailableSpaceBytes(final int n) {
        return 0;
    }
    
    public long getPlaybackHeadPosition() {
        long min = 0L;
        if (this.stopTimestampUs != -1L) {
            min = Math.min(this.endPlaybackHeadPosition, (SystemClock.elapsedRealtime() * 1000L - this.stopTimestampUs) * this.sampleRate / 1000000L + this.stopPlaybackHeadPosition);
        }
        else {
            final int playState = this.audioTrack.getPlayState();
            if (playState != 1) {
                final long n = this.audioTrack.getPlaybackHeadPosition();
                long lastRawPlaybackHeadPosition;
                if (n < 0L) {
                    lastRawPlaybackHeadPosition = 0L;
                }
                else {
                    lastRawPlaybackHeadPosition = (n & 0xFFFFFFFFL);
                }
                if (needPlaybackHeadPositionWorkaround()) {
                    if (this.lastRawPlaybackHeadPosition > lastRawPlaybackHeadPosition) {
                        this.frameCountBeforeWrapAround = this.lastRawPlaybackHeadPosition;
                    }
                    this.lastRawPlaybackHeadPosition = lastRawPlaybackHeadPosition;
                    return this.frameCountBeforeWrapAround + lastRawPlaybackHeadPosition;
                }
                long lastRawPlaybackHeadPosition2 = lastRawPlaybackHeadPosition;
                if (this.needsPassthroughWorkaround) {
                    if (playState == 2 && lastRawPlaybackHeadPosition == 0L) {
                        this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
                    }
                    lastRawPlaybackHeadPosition2 = lastRawPlaybackHeadPosition + this.passthroughWorkaroundPauseOffset;
                }
                if (this.lastRawPlaybackHeadPosition > lastRawPlaybackHeadPosition2) {
                    ++this.rawPlaybackHeadWrapCount;
                }
                this.lastRawPlaybackHeadPosition = lastRawPlaybackHeadPosition2;
                return (this.rawPlaybackHeadWrapCount << 32) + lastRawPlaybackHeadPosition2;
            }
        }
        return min;
    }
    
    public long getPlaybackHeadPositionUs() {
        return this.getPlaybackHeadPosition() * 1000000L / this.sampleRate;
    }
    
    public float getPlaybackSpeed() {
        return 1.0f;
    }
    
    public long getTimestampFramePosition() {
        throw new UnsupportedOperationException();
    }
    
    public long getTimestampNanoTime() {
        throw new UnsupportedOperationException();
    }
    
    public void handleEndOfStream(final long endPlaybackHeadPosition) {
        this.stopPlaybackHeadPosition = this.getPlaybackHeadPosition();
        this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000L;
        this.endPlaybackHeadPosition = endPlaybackHeadPosition;
        this.audioTrack.stop();
    }
    
    public void pause() {
        if (this.stopTimestampUs != -1L) {
            return;
        }
        this.audioTrack.pause();
    }
    
    void pcmFrameSubmitted(final int n) {
    }
    
    void play() {
    }
    
    public void reconfigure(final AudioTrack audioTrack, final boolean needsPassthroughWorkaround) {
        this.audioTrack = audioTrack;
        this.needsPassthroughWorkaround = needsPassthroughWorkaround;
        this.stopTimestampUs = -1L;
        this.lastRawPlaybackHeadPosition = 0L;
        this.rawPlaybackHeadWrapCount = 0L;
        this.passthroughWorkaroundPauseOffset = 0L;
        if (audioTrack != null) {
            this.sampleRate = audioTrack.getSampleRate();
        }
        this.frameCountBeforeWrapAround = 0L;
    }
    
    void setBufferFrameSize(final int n, final int n2) {
    }
    
    public void setPlaybackParameters(final PlaybackParams playbackParams) {
        throw new UnsupportedOperationException();
    }
    
    public boolean updateTimestamp() {
        return false;
    }
}
