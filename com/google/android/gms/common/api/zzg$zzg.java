// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzt;
import android.os.Bundle;

class zzg$zzg implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ zzg zzZK;
    
    private zzg$zzg(final zzg zzZK) {
        this.zzZK = zzZK;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzZK.zzZA.zza(new zzg$zzb(this.zzZK));
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzZK.zzZs.lock();
        try {
            if (this.zzZK.zze(connectionResult)) {
                this.zzZK.zznu();
                this.zzZK.zzns();
            }
            else {
                this.zzZK.zzf(connectionResult);
            }
        }
        finally {
            this.zzZK.zzZs.unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
}
