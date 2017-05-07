// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

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
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

class zzg$zzf extends zzg$zzi
{
    final /* synthetic */ zzg zzZK;
    private final ArrayList<Api$zzb> zzZW;
    
    public zzg$zzf(final zzg zzZK, final ArrayList<Api$zzb> zzZW) {
        this.zzZK = zzZK;
        super(zzZK, null);
        this.zzZW = zzZW;
    }
    
    public void zznn() {
        Set<Scope> set = this.zzZK.zzZq.zzaah;
        if (set.isEmpty()) {
            set = this.zzZK.zznw();
        }
        final Iterator<Api$zzb> iterator = this.zzZW.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this.zzZK.zzZE, set);
        }
    }
}
