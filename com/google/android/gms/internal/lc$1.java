// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.fitness.request.v$a;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.data.Session;

class lc$1 extends kj$c
{
    final /* synthetic */ Session TN;
    final /* synthetic */ lc TO;
    
    lc$1(final lc to, final Session tn) {
        this.TO = to;
        this.TN = tn;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new v$a().b(this.TN).jx(), new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
