// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$LoadRequestSummariesResult;

class RequestsImpl$LoadRequestSummariesImpl$1 implements Requests$LoadRequestSummariesResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ RequestsImpl$LoadRequestSummariesImpl Zw;
    
    RequestsImpl$LoadRequestSummariesImpl$1(final RequestsImpl$LoadRequestSummariesImpl zw, final Status cw) {
        this.Zw = zw;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
