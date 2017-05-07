// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import android.os.Looper;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

class lt$1 extends lt$a
{
    final /* synthetic */ LocationRequest aet;
    final /* synthetic */ LocationListener aeu;
    final /* synthetic */ lt aev;
    
    lt$1(final lt aev, final LocationRequest aet, final LocationListener aeu) {
        this.aev = aev;
        this.aet = aet;
        this.aeu = aeu;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.requestLocationUpdates(this.aet, this.aeu, null);
        this.b((R)Status.Jo);
    }
}
