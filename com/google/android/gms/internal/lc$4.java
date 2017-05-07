// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

class lc$4 extends kj$a<SessionReadResult>
{
    final /* synthetic */ lc TO;
    final /* synthetic */ SessionReadRequest TR;
    
    lc$4(final lc to, final SessionReadRequest tr) {
        this.TO = to;
        this.TR = tr;
    }
    
    protected SessionReadResult C(final Status status) {
        return SessionReadResult.H(status);
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TR, new lc$a(this, null), kj.getContext().getPackageName());
    }
}
