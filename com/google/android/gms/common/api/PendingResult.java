// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

public interface PendingResult<R extends Result>
{
    void setResultCallback(final ResultCallback<R> p0);
    
    void setResultCallback(final ResultCallback<R> p0, final long p1, final TimeUnit p2);
}
