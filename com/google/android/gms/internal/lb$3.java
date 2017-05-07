// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.fitness.request.DataSourceListener;
import com.google.android.gms.common.api.Status;

class lb$3 extends lb$a<Status>
{
    final /* synthetic */ lb TI;
    final /* synthetic */ DataSourceListener TK;
    final /* synthetic */ p TL;
    
    lb$3(final lb ti, final DataSourceListener tk, final p tl) {
        this.TI = ti;
        this.TK = tk;
        this.TL = tl;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TL, new lb$c(this, this.TK, null), kj.getContext().getPackageName());
    }
    
    protected Status d(final Status status) {
        return status;
    }
}
