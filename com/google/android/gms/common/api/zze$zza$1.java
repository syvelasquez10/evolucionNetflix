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
import com.google.android.gms.common.ConnectionResult;

class zze$zza$1 implements Runnable
{
    final /* synthetic */ zze zzXe;
    final /* synthetic */ ConnectionResult zzXf;
    final /* synthetic */ zze$zza zzXg;
    
    zze$zza$1(final zze$zza zzXg, final zze zzXe, final ConnectionResult zzXf) {
        this.zzXg = zzXg;
        this.zzXe = zzXe;
        this.zzXf = zzXf;
    }
    
    @Override
    public void run() {
        this.zzXe.zza(this.zzXf);
    }
}
