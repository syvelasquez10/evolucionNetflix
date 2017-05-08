// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

final class MediaCodecSelector$1 implements MediaCodecSelector
{
    @Override
    public DecoderInfo getDecoderInfo(final String s, final boolean b) {
        return MediaCodecUtil.getDecoderInfo(s, b);
    }
    
    @Override
    public DecoderInfo getPassthroughDecoderInfo() {
        return MediaCodecUtil.getPassthroughDecoderInfo();
    }
}
