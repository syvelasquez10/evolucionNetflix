// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

class ky$3 extends kj$a<DataReadResult>
{
    final /* synthetic */ DataReadRequest TA;
    final /* synthetic */ ky Ty;
    
    ky$3(final ky ty, final DataReadRequest ta) {
        this.Ty = ty;
        this.TA = ta;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.TA, new ky$a(this, null), kj.getContext().getPackageName());
    }
    
    protected DataReadResult y(final Status status) {
        return DataReadResult.a(status, this.TA);
    }
}
