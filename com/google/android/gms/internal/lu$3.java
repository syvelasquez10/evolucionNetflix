// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import com.google.android.gms.common.api.Api$a;
import java.util.List;

class lu$3 extends lu$a
{
    final /* synthetic */ lu aeB;
    final /* synthetic */ List aeE;
    
    lu$3(final lu aeB, final List aeE) {
        this.aeB = aeB;
        this.aeE = aeE;
        super((lu$1)null);
    }
    
    @Override
    protected void a(final ly ly) {
        ly.removeGeofences(this.aeE, new lu$3$1(this));
    }
}
