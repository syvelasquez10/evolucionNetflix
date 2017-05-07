// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Set;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import java.util.Iterator;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import com.google.android.gms.common.api.Api$zzb;
import java.util.Map;

class zzlg$zze extends zzlg$zzi
{
    final /* synthetic */ zzlg zzabL;
    private final Map<Api$zzb, GoogleApiClient$zza> zzabU;
    
    public zzlg$zze(final zzlg zzabL, final Map<Api$zzb, GoogleApiClient$zza> zzabU) {
        this.zzabL = zzabL;
        super(zzabL, null);
        this.zzabU = zzabU;
    }
    
    public void zznO() {
        final int googlePlayServicesAvailable = this.zzabL.zzaaP.isGooglePlayServicesAvailable(this.zzabL.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.zzabL.zzabr.zza((zzli$zzb)new zzlg$zze$1(this, this.zzabL, new ConnectionResult(googlePlayServicesAvailable, null)));
        }
        else {
            if (this.zzabL.zzabD) {
                this.zzabL.zzabB.connect();
            }
            for (final Api$zzb api$zzb : this.zzabU.keySet()) {
                api$zzb.zza(this.zzabU.get(api$zzb));
            }
        }
    }
}
