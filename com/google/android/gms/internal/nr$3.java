// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.plus.model.moments.Moment;

class nr$3 extends nr$c
{
    final /* synthetic */ nr alD;
    final /* synthetic */ Moment alI;
    
    nr$3(final nr alD, final Moment alI) {
        this.alD = alD;
        this.alI = alI;
        super((nr$1)null);
    }
    
    @Override
    protected void a(final e e) {
        e.a((BaseImplementation$b<Status>)this, this.alI);
    }
}
