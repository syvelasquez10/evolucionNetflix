// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

class kx$1 extends kj$a<DataTypeResult>
{
    final /* synthetic */ DataTypeCreateRequest Tu;
    final /* synthetic */ kx Tv;
    
    kx$1(final kx tv, final DataTypeCreateRequest tu) {
        this.Tv = tv;
        this.Tu = tu;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.Tu, new kx$a(this, null), kj.getContext().getPackageName());
    }
    
    protected DataTypeResult x(final Status status) {
        return DataTypeResult.F(status);
    }
}
