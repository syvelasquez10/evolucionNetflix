// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodec$CryptoException;

public interface MediaCodecTrackRenderer$EventListener
{
    void onCryptoError(final MediaCodec$CryptoException p0);
    
    void onDecoderInitializationError(final MediaCodecTrackRenderer$DecoderInitializationException p0);
    
    void onDecoderInitialized(final String p0, final long p1, final long p2);
}
