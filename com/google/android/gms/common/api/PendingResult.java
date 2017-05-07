// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

public abstract class PendingResult<R extends Result>
{
    public abstract void setResultCallback(final ResultCallback<? super R> p0);
    
    public abstract void setResultCallback(final ResultCallback<? super R> p0, final long p1, final TimeUnit p2);
    
    public Integer zznF() {
        throw new UnsupportedOperationException();
    }
}
