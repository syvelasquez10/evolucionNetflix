// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.DataInsertRequest;

class ky$1 extends kj$c
{
    final /* synthetic */ DataInsertRequest Tx;
    final /* synthetic */ ky Ty;
    
    ky$1(final ky ty, final DataInsertRequest tx) {
        this.Ty = ty;
        this.Tx = tx;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.Tx, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
