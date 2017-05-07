// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.common.api.Api$zzb;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Set;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzt;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;

class zzlg$zzg implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ zzlg zzabL;
    
    private zzlg$zzg(final zzlg zzabL) {
        this.zzabL = zzabL;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzabL.zzabB.zza(new zzlg$zzb(this.zzabL));
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzabL.zzabt.lock();
        try {
            if (this.zzabL.zze(connectionResult)) {
                this.zzabL.zznV();
                this.zzabL.zznT();
            }
            else {
                this.zzabL.zzf(connectionResult);
            }
        }
        finally {
            this.zzabL.zzabt.unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
}
