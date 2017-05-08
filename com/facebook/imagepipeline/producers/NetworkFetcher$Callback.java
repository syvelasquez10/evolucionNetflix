// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.InputStream;

public interface NetworkFetcher$Callback
{
    void onCancellation();
    
    void onFailure(final Throwable p0);
    
    void onResponse(final InputStream p0, final int p1);
}
