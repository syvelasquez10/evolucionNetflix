// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;
import com.google.android.gms.common.api.Api$a;
import java.util.List;
import android.app.PendingIntent;

class lu$1 extends lu$a
{
    final /* synthetic */ PendingIntent aeA;
    final /* synthetic */ lu aeB;
    final /* synthetic */ List aez;
    
    lu$1(final lu aeB, final List aez, final PendingIntent aeA) {
        this.aeB = aeB;
        this.aez = aez;
        this.aeA = aeA;
        super((lu$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.addGeofences(this.aez, this.aeA, new lu$1$1(this));
    }
}
