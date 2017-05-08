// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo;

interface MediaCodecUtil$MediaCodecListCompat
{
    int getCodecCount();
    
    MediaCodecInfo getCodecInfoAt(final int p0);
    
    boolean isSecurePlaybackSupported(final String p0, final MediaCodecInfo$CodecCapabilities p1);
    
    boolean secureDecodersExplicit();
}
