// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.net;

public final class BandwithLimit
{
    public static final int PLAYBACK_ALLOWED_NO_LIMIT = 0;
    public static final int PLAYBACK_ALLOWED_WITH_LIMIT = 1;
    public static final int PLAYBACK_NOT_ALLOWED = -1;
    private String carrier;
    private int maxVideoBitrate;
    private LogMobileType networkType;
    private int playbackStatus;
    
    public BandwithLimit(final String carrier, final int maxVideoBitrate, final int playbackStatus, final LogMobileType networkType) {
        if (carrier == null) {
            this.carrier = "";
        }
        else {
            this.carrier = carrier;
        }
        this.isValid(playbackStatus);
        if (networkType == null) {
            throw new IllegalArgumentException("If network type is null");
        }
        if (maxVideoBitrate <= 100 && maxVideoBitrate != 0) {
            throw new IllegalArgumentException("Invalid maximal bitrate " + maxVideoBitrate);
        }
        this.maxVideoBitrate = maxVideoBitrate;
        this.playbackStatus = playbackStatus;
        this.networkType = networkType;
    }
    
    private boolean isValid(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Not valid playback status!");
            }
            case -1:
            case 0:
            case 1: {
                return true;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof BandwithLimit)) {
                return false;
            }
            final BandwithLimit bandwithLimit = (BandwithLimit)o;
            if (this.carrier == null) {
                if (bandwithLimit.carrier != null) {
                    return false;
                }
            }
            else if (!this.carrier.equals(bandwithLimit.carrier)) {
                return false;
            }
            if (this.maxVideoBitrate != bandwithLimit.maxVideoBitrate) {
                return false;
            }
            if (this.networkType == null) {
                if (bandwithLimit.networkType != null) {
                    return false;
                }
            }
            else if (!this.networkType.equals(bandwithLimit.networkType)) {
                return false;
            }
            if (this.playbackStatus != bandwithLimit.playbackStatus) {
                return false;
            }
        }
        return true;
    }
    
    public String getCarrier() {
        return this.carrier;
    }
    
    public int getMaxVideoBitrate() {
        return this.maxVideoBitrate;
    }
    
    public LogMobileType getNetworkType() {
        return this.networkType;
    }
    
    public int getPlaybackStatus() {
        return this.playbackStatus;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.carrier == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.carrier.hashCode();
        }
        final int maxVideoBitrate = this.maxVideoBitrate;
        if (this.networkType != null) {
            hashCode = this.networkType.hashCode();
        }
        return (((hashCode2 + 31) * 31 + maxVideoBitrate) * 31 + hashCode) * 31 + this.playbackStatus;
    }
    
    @Override
    public String toString() {
        return "BandwithLimit [carrier=" + this.carrier + ", maxVideoBitrate=" + this.maxVideoBitrate + ", networkType=" + this.networkType + ", playbackStatus=" + this.playbackStatus + "]";
    }
}
