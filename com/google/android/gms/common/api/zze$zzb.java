// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.app.PendingIntent;
import android.util.Log;
import java.util.HashSet;
import java.util.Map;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.internal.zzq$zza;

class zze$zzb extends zzq$zza
{
    private final WeakReference<zze> zzXd;
    
    zze$zzb(final zze zze) {
        this.zzXd = new WeakReference<zze>(zze);
    }
    
    public void zzb(final ResolveAccountResponse resolveAccountResponse) {
        final zze zze = this.zzXd.get();
        if (zze == null) {
            return;
        }
        zze.zzWJ.zzXr.post((Runnable)new zze$zzb$1(this, zze, resolveAccountResponse));
    }
}
