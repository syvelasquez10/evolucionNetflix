// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.common.api.Api$a;

class kw$3 extends kj$c
{
    final /* synthetic */ kw Tq;
    final /* synthetic */ String Ts;
    
    kw$3(final kw tq, final String ts) {
        this.Tq = tq;
        this.Ts = ts;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new b(this.Ts), new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
