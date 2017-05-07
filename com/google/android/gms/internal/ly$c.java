// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationClient$OnRemoveGeofencesResultListener;
import android.location.Location;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.location.LocationClient$OnAddGeofencesResultListener;
import android.app.PendingIntent;
import java.util.List;
import android.os.IBinder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;
import android.os.IInterface;

final class ly$c implements md<lw>
{
    final /* synthetic */ ly aeR;
    
    private ly$c(final ly aeR) {
        this.aeR = aeR;
    }
    
    @Override
    public void dK() {
        this.aeR.dK();
    }
    
    public lw lX() {
        return this.aeR.gS();
    }
}
