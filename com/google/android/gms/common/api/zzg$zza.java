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
import android.os.Bundle;
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
import com.google.android.gms.signin.internal.AuthAccountResult;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;
import com.google.android.gms.signin.internal.zzb;

class zzg$zza extends zzb
{
    private final WeakReference<zzg> zzZL;
    
    zzg$zza(final zzg zzg) {
        this.zzZL = new WeakReference<zzg>(zzg);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final AuthAccountResult authAccountResult) {
        final zzg zzg = this.zzZL.get();
        if (zzg == null) {
            return;
        }
        zzg.zzZq.zza((zzi$zzb)new zzg$zza$1(this, zzg, zzg, connectionResult));
    }
}
