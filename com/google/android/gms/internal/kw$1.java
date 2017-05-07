// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.StartBleScanRequest;

class kw$1 extends kj$c
{
    final /* synthetic */ StartBleScanRequest Tp;
    final /* synthetic */ kw Tq;
    
    kw$1(final kw tq, final StartBleScanRequest tp) {
        this.Tq = tq;
        this.Tp = tp;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.Tp, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
