// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.x$a;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.SessionStopResult;

class lc$2 extends kj$a<SessionStopResult>
{
    final /* synthetic */ lc TO;
    final /* synthetic */ String TP;
    final /* synthetic */ String val$name;
    
    lc$2(final lc to, final String val$name, final String tp) {
        this.TO = to;
        this.val$name = val$name;
        this.TP = tp;
    }
    
    protected SessionStopResult B(final Status status) {
        return SessionStopResult.I(status);
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new x$a().br(this.val$name).bs(this.TP).jy(), new lc$b(this, null), kj.getContext().getPackageName());
    }
}
