// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.SessionInsertRequest;

class lc$3 extends kj$c
{
    final /* synthetic */ lc TO;
    final /* synthetic */ SessionInsertRequest TQ;
    
    lc$3(final lc to, final SessionInsertRequest tq) {
        this.TO = to;
        this.TQ = tq;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TQ, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
