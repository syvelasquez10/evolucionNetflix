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
import java.util.Collections;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.Future;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import java.util.Iterator;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.api.Api$zzb;
import java.util.ArrayList;

class zzlg$zzf extends zzlg$zzi
{
    final /* synthetic */ zzlg zzabL;
    private final ArrayList<Api$zzb> zzabX;
    
    public zzlg$zzf(final zzlg zzabL, final ArrayList<Api$zzb> zzabX) {
        this.zzabL = zzabL;
        super(zzabL, null);
        this.zzabX = zzabX;
    }
    
    public void zznO() {
        Set<Scope> set = this.zzabL.zzabr.zzaci;
        if (set.isEmpty()) {
            set = this.zzabL.zznX();
        }
        final Iterator<Api$zzb> iterator = this.zzabX.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this.zzabL.zzabF, set);
        }
    }
}
