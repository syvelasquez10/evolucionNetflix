// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$SendRequestResult;

class RequestsImpl$SendRequestImpl$1 implements Requests$SendRequestResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ RequestsImpl$SendRequestImpl Zy;
    
    RequestsImpl$SendRequestImpl$1(final RequestsImpl$SendRequestImpl zy, final Status cw) {
        this.Zy = zy;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
