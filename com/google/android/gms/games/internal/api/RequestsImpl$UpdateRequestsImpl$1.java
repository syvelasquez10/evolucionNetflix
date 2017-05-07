// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import java.util.Set;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;

class RequestsImpl$UpdateRequestsImpl$1 implements Requests$UpdateRequestsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ RequestsImpl$UpdateRequestsImpl Zz;
    
    RequestsImpl$UpdateRequestsImpl$1(final RequestsImpl$UpdateRequestsImpl zz, final Status cw) {
        this.Zz = zz;
        this.CW = cw;
    }
    
    @Override
    public Set<String> getRequestIds() {
        return null;
    }
    
    @Override
    public int getRequestOutcome(final String s) {
        throw new IllegalArgumentException("Unknown request ID " + s);
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
