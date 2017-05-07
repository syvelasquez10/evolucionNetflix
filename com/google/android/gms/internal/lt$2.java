// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import android.location.Location;

class lt$2 extends lt$a
{
    final /* synthetic */ lt aev;
    final /* synthetic */ Location aew;
    
    lt$2(final lt aev, final Location aew) {
        this.aev = aev;
        this.aew = aew;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.setMockLocation(this.aew);
        this.b((R)Status.Jo);
    }
}
