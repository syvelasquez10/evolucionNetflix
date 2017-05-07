// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.nio.ByteBuffer;

public interface MediaDecoderPipe$InputDataSource
{
    MediaDecoderPipe$InputDataSource$BufferMeta onRequestData(final ByteBuffer p0);
}
