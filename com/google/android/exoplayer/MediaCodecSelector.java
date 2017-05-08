// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public interface MediaCodecSelector
{
    public static final MediaCodecSelector DEFAULT = new MediaCodecSelector$1();
    
    DecoderInfo getDecoderInfo(final String p0, final boolean p1);
    
    DecoderInfo getPassthroughDecoderInfo();
}
