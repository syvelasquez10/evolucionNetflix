// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.api.Api$a;

class kx$3 extends kj$c
{
    final /* synthetic */ kx Tv;
    
    kx$3(final kx tv) {
        this.Tv = tv;
    }
    
    @Override
    protected void a(final kj kj) {
        kj.iT().a(new kj$b((BaseImplementation$b<Status>)this), kj.getContext().getPackageName());
    }
}
