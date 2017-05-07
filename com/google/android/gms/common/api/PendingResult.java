// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

public interface PendingResult<R extends Result>
{
    void a(final a p0);
    
    R await();
    
    R await(final long p0, final TimeUnit p1);
    
    void cancel();
    
    boolean isCanceled();
    
    void setResultCallback(final ResultCallback<R> p0);
    
    void setResultCallback(final ResultCallback<R> p0, final long p1, final TimeUnit p2);
    
    public interface a
    {
        void l(final Status p0);
    }
}
