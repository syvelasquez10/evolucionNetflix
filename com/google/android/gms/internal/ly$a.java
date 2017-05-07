// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.common.internal.d;
import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;
import com.google.android.gms.common.internal.d$b;

final class ly$a extends d$b<LocationClient$OnAddGeofencesResultListener>
{
    private final int HF;
    private final String[] aeQ;
    final /* synthetic */ ly aeR;
    
    public ly$a(final ly aeR, final LocationClient$OnAddGeofencesResultListener locationClient$OnAddGeofencesResultListener, final int n, final String[] aeQ) {
        this.aeR = aeR;
        super(locationClient$OnAddGeofencesResultListener);
        this.HF = LocationStatusCodes.ee(n);
        this.aeQ = aeQ;
    }
    
    protected void a(final LocationClient$OnAddGeofencesResultListener locationClient$OnAddGeofencesResultListener) {
        if (locationClient$OnAddGeofencesResultListener != null) {
            locationClient$OnAddGeofencesResultListener.onAddGeofencesResult(this.HF, this.aeQ);
        }
    }
    
    @Override
    protected void gT() {
    }
}
