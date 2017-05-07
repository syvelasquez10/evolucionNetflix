// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient$zza;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Iterator;
import com.google.android.gms.common.api.Api$zzb;
import android.app.PendingIntent;
import android.util.Log;
import java.util.HashSet;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
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
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.internal.zzt$zza;

class zzlg$zzb extends zzt$zza
{
    private final WeakReference<zzlg> zzabM;
    
    zzlg$zzb(final zzlg zzlg) {
        this.zzabM = new WeakReference<zzlg>(zzlg);
    }
    
    public void zzb(final ResolveAccountResponse resolveAccountResponse) {
        final zzlg zzlg = this.zzabM.get();
        if (zzlg == null) {
            return;
        }
        zzlg.zzabr.zza((zzli$zzb)new zzlg$zzb$1(this, zzlg, zzlg, resolveAccountResponse));
    }
}
