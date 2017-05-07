// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$a;
import android.os.Looper;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

class lt$3 extends lt$a
{
    final /* synthetic */ LocationRequest aet;
    final /* synthetic */ LocationListener aeu;
    final /* synthetic */ lt aev;
    final /* synthetic */ Looper aex;
    
    lt$3(final lt aev, final LocationRequest aet, final LocationListener aeu, final Looper aex) {
        this.aev = aev;
        this.aet = aet;
        this.aeu = aeu;
        this.aex = aex;
        super((lt$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.requestLocationUpdates(this.aet, this.aeu, this.aex);
        this.b((R)Status.Jo);
    }
}
