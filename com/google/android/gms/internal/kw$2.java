// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.ac;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.BleScanCallback;

class kw$2 extends kj$c
{
    final /* synthetic */ kw Tq;
    final /* synthetic */ BleScanCallback Tr;
    
    kw$2(final kw tq, final BleScanCallback tr) {
        this.Tq = tq;
        this.Tr = tr;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new ac(this.Tr), new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
