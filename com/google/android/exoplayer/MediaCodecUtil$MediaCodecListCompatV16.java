// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

final class MediaCodecUtil$MediaCodecListCompatV16 implements MediaCodecUtil$MediaCodecListCompat
{
    @Override
    public int getCodecCount() {
        return MediaCodecList.getCodecCount();
    }
    
    @Override
    public MediaCodecInfo getCodecInfoAt(final int n) {
        return MediaCodecList.getCodecInfoAt(n);
    }
    
    @Override
    public boolean isSecurePlaybackSupported(final String s, final MediaCodecInfo$CodecCapabilities mediaCodecInfo$CodecCapabilities) {
        return "video/avc".equals(s);
    }
    
    @Override
    public boolean secureDecodersExplicit() {
        return false;
    }
}
