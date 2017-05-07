// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

public final class PlayoutMetadata
{
    private static final long DELTA_CURRENT = 1000L;
    public int audioTrackType;
    public int duration;
    public int instantBitRate;
    public boolean isHD;
    public boolean isSuperHD;
    public String language;
    public int numChannels;
    public int position;
    public int targetBitRate;
    private long timestamp;
    
    public PlayoutMetadata(final int position, final int duration, final int instantBitRate, final int targetBitRate, final boolean isHD, final boolean isSuperHD, final String language, final int numChannels, final int audioTrackType) {
        this.timestamp = System.currentTimeMillis();
        this.position = position;
        this.duration = duration;
        this.instantBitRate = instantBitRate;
        this.targetBitRate = targetBitRate;
        this.isHD = isHD;
        this.isSuperHD = isSuperHD;
        this.language = language;
        this.numChannels = numChannels;
        this.audioTrackType = audioTrackType;
    }
    
    public String getAudioChannel() {
        switch (this.numChannels) {
            default: {
                return String.valueOf(this.numChannels);
            }
            case 1: {
                return "Mono";
            }
            case 2: {
                return "Stereo";
            }
            case 6: {
                return "5.1";
            }
            case 7: {
                return "6.1";
            }
            case 8: {
                return "7.1";
            }
        }
    }
    
    public String getAudioTrackType() {
        switch (this.audioTrackType) {
            default: {
                return "uknown";
            }
            case 0: {
                return "primary";
            }
            case 1: {
                return "commentary";
            }
            case 2: {
                return "assitive";
            }
        }
    }
    
    public boolean isCurrent() {
        return System.currentTimeMillis() - 1000L < this.timestamp;
    }
    
    @Override
    public String toString() {
        return "PlayoutMetadata [timestamp=" + this.timestamp + ", position=" + this.position + ", duration=" + this.duration + ", instantBitRate=" + this.instantBitRate + ", targetBitRate=" + this.targetBitRate + ", isHD=" + this.isHD + ", isSuperHD=" + this.isSuperHD + ", language=" + this.language + ", numChannels=" + this.getAudioChannel() + ", audioTrackType=" + this.getAudioTrackType() + "]";
    }
}
