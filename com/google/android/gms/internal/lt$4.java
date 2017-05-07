// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.location.LocationRequest;
import android.app.PendingIntent;

class lt$4 extends lt$a
{
    final /* synthetic */ PendingIntent aer;
    final /* synthetic */ LocationRequest aet;
    final /* synthetic */ lt aev;
    
    lt$4(final lt aev, final LocationRequest aet, final PendingIntent aer) {
        this.aev = aev;
        this.aet = aet;
        this.aer = aer;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.requestLocationUpdates(this.aet, this.aer);
        this.b((R)Status.Jo);
    }
}
