// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import java.util.Collections;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;
import java.util.Iterator;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import java.util.Map;

class zzg$zze extends zzg$zzi
{
    final /* synthetic */ zzg zzZK;
    private final Map<Api$zzb, GoogleApiClient$zza> zzZT;
    
    public zzg$zze(final zzg zzZK, final Map<Api$zzb, GoogleApiClient$zza> zzZT) {
        this.zzZK = zzZK;
        super(zzZK, null);
        this.zzZT = zzZT;
    }
    
    public void zznn() {
        final int googlePlayServicesAvailable = this.zzZK.zzZi.isGooglePlayServicesAvailable(this.zzZK.mContext);
        if (googlePlayServicesAvailable != 0) {
            this.zzZK.zzZq.zza((zzi$zzb)new zzg$zze$1(this, this.zzZK, new ConnectionResult(googlePlayServicesAvailable, null)));
        }
        else {
            if (this.zzZK.zzZC) {
                this.zzZK.zzZA.connect();
            }
            for (final Api$zzb api$zzb : this.zzZT.keySet()) {
                api$zzb.zza(this.zzZT.get(api$zzb));
            }
        }
    }
}
