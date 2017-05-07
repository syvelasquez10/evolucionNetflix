// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;

class lt$7 extends lt$a
{
    final /* synthetic */ lt aev;
    final /* synthetic */ boolean aey;
    
    lt$7(final lt aev, final boolean aey) {
        this.aev = aev;
        this.aey = aey;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.setMockMode(this.aey);
        this.b((R)Status.Jo);
    }
}
