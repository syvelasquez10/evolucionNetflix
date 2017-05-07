// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R>
{
    public abstract void onFailure(final Status p0);
    
    public abstract void onSuccess(final R p0);
}
