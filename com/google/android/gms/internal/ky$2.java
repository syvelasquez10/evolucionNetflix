// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.fitness.request.DataDeleteRequest;

class ky$2 extends kj$c
{
    final /* synthetic */ ky Ty;
    final /* synthetic */ DataDeleteRequest Tz;
    
    ky$2(final ky ty, final DataDeleteRequest tz) {
        this.Ty = ty;
        this.Tz = tz;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(this.Tz, new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
