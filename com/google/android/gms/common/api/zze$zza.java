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
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Map;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.signin.internal.AuthAccountResult;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;
import com.google.android.gms.signin.internal.zzb;

class zze$zza extends zzb
{
    private final WeakReference<com.google.android.gms.common.api.zze> zzXd;
    
    zze$zza(final com.google.android.gms.common.api.zze zze) {
        this.zzXd = new WeakReference<com.google.android.gms.common.api.zze>(zze);
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult, final AuthAccountResult authAccountResult) {
        final com.google.android.gms.common.api.zze zze = this.zzXd.get();
        if (zze == null) {
            return;
        }
        zze.zzWJ.zzXr.post((Runnable)new zze$zza$1(this, zze, connectionResult));
    }
}
