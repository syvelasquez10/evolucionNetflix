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

class zze$zzb$1 implements Runnable
{
    final /* synthetic */ zze zzXe;
    final /* synthetic */ ResolveAccountResponse zzXh;
    final /* synthetic */ zze$zzb zzXi;
    
    zze$zzb$1(final zze$zzb zzXi, final zze zzXe, final ResolveAccountResponse zzXh) {
        this.zzXi = zzXi;
        this.zzXe = zzXe;
        this.zzXh = zzXh;
    }
    
    @Override
    public void run() {
        this.zzXe.zza(this.zzXh);
    }
}
