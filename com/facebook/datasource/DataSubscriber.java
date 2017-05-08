// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

public interface DataSubscriber<T>
{
    void onCancellation(final DataSource<T> p0);
    
    void onFailure(final DataSource<T> p0);
    
    void onNewResult(final DataSource<T> p0);
    
    void onProgressUpdate(final DataSource<T> p0);
}
