// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.data.BleDevice;

class kw$4 extends kj$c
{
    final /* synthetic */ kw Tq;
    final /* synthetic */ BleDevice Tt;
    
    kw$4(final kw tq, final BleDevice tt) {
        this.Tq = tq;
        this.Tt = tt;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new b(this.Tt), new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
