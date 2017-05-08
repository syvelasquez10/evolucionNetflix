// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

public interface Consumer<T>
{
    void onCancellation();
    
    void onFailure(final Throwable p0);
    
    void onNewResult(final T p0, final boolean p1);
    
    void onProgressUpdate(final float p0);
}
