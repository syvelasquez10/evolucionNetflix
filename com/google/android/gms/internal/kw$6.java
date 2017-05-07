// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.result.BleDevicesResult;

class kw$6 extends kj$a<BleDevicesResult>
{
    final /* synthetic */ kw Tq;
    
    kw$6(final kw tq) {
        this.Tq = tq;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new kw$a(this, null), kj.getContext().getPackageName());
    }
    
    protected BleDevicesResult w(final Status status) {
        return BleDevicesResult.D(status);
    }
}
