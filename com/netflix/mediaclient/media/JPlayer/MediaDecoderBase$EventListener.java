// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

public interface MediaDecoderBase$EventListener
{
    void onDecoderReady(final boolean p0);
    
    void onDecoderStarted(final boolean p0);
    
    void onEndOfStream(final boolean p0);
    
    void onError(final boolean p0, final int p1, final String p2);
    
    void onFlushed(final boolean p0);
    
    void onPasued(final boolean p0);
    
    void onSampleRendered(final boolean p0, final long p1, final long p2);
}
