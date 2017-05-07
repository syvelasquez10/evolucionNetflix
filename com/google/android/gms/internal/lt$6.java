// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import android.app.PendingIntent;

class lt$6 extends lt$a
{
    final /* synthetic */ PendingIntent aer;
    final /* synthetic */ lt aev;
    
    lt$6(final lt aev, final PendingIntent aer) {
        this.aev = aev;
        this.aer = aer;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.removeLocationUpdates(this.aer);
        this.b((R)Status.Jo);
    }
}
