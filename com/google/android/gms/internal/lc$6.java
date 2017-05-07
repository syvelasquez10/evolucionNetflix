// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.z;
import com.google.android.gms.common.api.Api$a;
import android.app.PendingIntent;

class lc$6 extends kj$c
{
    final /* synthetic */ lc TO;
    final /* synthetic */ PendingIntent TS;
    
    lc$6(final lc to, final PendingIntent ts) {
        this.TO = to;
        this.TS = ts;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new z(this.TS), new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
