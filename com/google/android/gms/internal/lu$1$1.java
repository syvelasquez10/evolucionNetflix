// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;

class lu$1$1 implements LocationClient$OnAddGeofencesResultListener
{
    final /* synthetic */ lu$1 aeC;
    
    lu$1$1(final lu$1 aeC) {
        this.aeC = aeC;
    }
    
    @Override
    public void onAddGeofencesResult(final int n, final String[] array) {
        this.aeC.b((R)LocationStatusCodes.ef(n));
    }
}
