// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

import java.util.concurrent.Executor;

public interface DataSource<T>
{
    boolean close();
    
    Throwable getFailureCause();
    
    float getProgress();
    
    T getResult();
    
    boolean hasResult();
    
    boolean isFinished();
    
    void subscribe(final DataSubscriber<T> p0, final Executor p1);
}
