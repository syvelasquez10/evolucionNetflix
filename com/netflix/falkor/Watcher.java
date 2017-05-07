// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public interface Watcher<T>
{
    void onCompleted();
    
    void onError(final Exception p0);
    
    void onNext(final T p0);
}
