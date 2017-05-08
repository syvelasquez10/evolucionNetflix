// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaCodecInfo;
import android.annotation.TargetApi;

@TargetApi(21)
final class MediaCodecUtil$MediaCodecListCompatV21 implements MediaCodecUtil$MediaCodecListCompat
{
    private final int codecKind;
    private MediaCodecInfo[] mediaCodecInfos;
    
    public MediaCodecUtil$MediaCodecListCompatV21(final boolean b) {
        int codecKind;
        if (b) {
            codecKind = 1;
        }
        else {
            codecKind = 0;
        }
        this.codecKind = codecKind;
    }
    
    private void ensureMediaCodecInfosInitialized() {
        if (this.mediaCodecInfos == null) {
            this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
        }
    }
    
    @Override
    public int getCodecCount() {
        this.ensureMediaCodecInfosInitialized();
        return this.mediaCodecInfos.length;
    }
    
    @Override
    public MediaCodecInfo getCodecInfoAt(final int n) {
        this.ensureMediaCodecInfosInitialized();
        return this.mediaCodecInfos[n];
    }
    
    @Override
    public boolean isSecurePlaybackSupported(final String s, final MediaCodecInfo$CodecCapabilities mediaCodecInfo$CodecCapabilities) {
        return mediaCodecInfo$CodecCapabilities.isFeatureSupported("secure-playback");
    }
    
    @Override
    public boolean secureDecodersExplicit() {
        return true;
    }
}
