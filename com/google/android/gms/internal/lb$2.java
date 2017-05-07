// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.common.api.Status;

class lb$2 extends lb$a<Status>
{
    final /* synthetic */ lb TI;
    final /* synthetic */ n TJ;
    
    lb$2(final lb ti, final n tj) {
        this.TI = ti;
        this.TJ = tj;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TJ, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
    
    protected Status d(final Status status) {
        return status;
    }
}
