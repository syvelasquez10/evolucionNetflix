// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;

class RequestsImpl$LoadRequestsImpl$1 implements Requests$LoadRequestsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ RequestsImpl$LoadRequestsImpl Zx;
    
    RequestsImpl$LoadRequestsImpl$1(final RequestsImpl$LoadRequestsImpl zx, final Status cw) {
        this.Zx = zx;
        this.CW = cw;
    }
    
    @Override
    public GameRequestBuffer getRequests(final int n) {
        return new GameRequestBuffer(DataHolder.as(this.CW.getStatusCode()));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
