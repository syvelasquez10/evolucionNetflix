// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import com.google.android.gms.common.api.Api$a;
import android.app.PendingIntent;

class lu$2 extends lu$a
{
    final /* synthetic */ PendingIntent aeA;
    final /* synthetic */ lu aeB;
    
    lu$2(final lu aeB, final PendingIntent aeA) {
        this.aeB = aeB;
        this.aeA = aeA;
        super((lu$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.removeGeofences(this.aeA, new lu$2$1(this));
    }
}
