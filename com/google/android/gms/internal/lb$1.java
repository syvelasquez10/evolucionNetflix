// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

class lb$1 extends lb$a<DataSourcesResult>
{
    final /* synthetic */ DataSourcesRequest TH;
    final /* synthetic */ lb TI;
    
    lb$1(final lb ti, final DataSourcesRequest th) {
        this.TI = ti;
        this.TH = th;
    }
    
    protected DataSourcesResult A(final Status status) {
        return DataSourcesResult.E(status);
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TH, new lb$b(this, null), kj.getContext().getPackageName());
    }
}
