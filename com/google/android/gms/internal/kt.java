// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.api.PendingResult$a;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

class kt<T extends Result> implements PendingResult<T>
{
    private final T Tn;
    
    kt(final T tn) {
        this.Tn = tn;
    }
    
    @Override
    public void a(final PendingResult$a pendingResult$a) {
        pendingResult$a.n(this.Tn.getStatus());
    }
    
    @Override
    public T await() {
        return this.Tn;
    }
    
    @Override
    public T await(final long n, final TimeUnit timeUnit) {
        return this.Tn;
    }
    
    @Override
    public void cancel() {
    }
    
    @Override
    public boolean isCanceled() {
        return false;
    }
    
    @Override
    public void setResultCallback(final ResultCallback<T> resultCallback) {
        resultCallback.onResult(this.Tn);
    }
    
    @Override
    public void setResultCallback(final ResultCallback<T> resultCallback, final long n, final TimeUnit timeUnit) {
        resultCallback.onResult(this.Tn);
    }
}
