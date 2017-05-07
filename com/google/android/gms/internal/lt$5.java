// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.location.LocationListener;

class lt$5 extends lt$a
{
    final /* synthetic */ LocationListener aeu;
    final /* synthetic */ lt aev;
    
    lt$5(final lt aev, final LocationListener aeu) {
        this.aev = aev;
        this.aeu = aeu;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.removeLocationUpdates(this.aeu);
        this.b((R)Status.Jo);
    }
}
