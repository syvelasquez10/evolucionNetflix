// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

public interface PendingResult<R extends Result>
{
    R await();
    
    R await(final long p0, final TimeUnit p1);
    
    R e(final Status p0);
    
    void setResultCallback(final ResultCallback<R> p0);
    
    public interface a
    {
        void l(final Status p0);
    }
}
