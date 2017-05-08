// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import java.io.IOException;

public class MediaCodecUtil$DecoderQueryException extends IOException
{
    private MediaCodecUtil$DecoderQueryException(final Throwable t) {
        super("Failed to query underlying media codecs", t);
    }
}
