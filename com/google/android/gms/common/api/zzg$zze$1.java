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
import com.google.android.gms.common.ConnectionResult;

class zzg$zze$1 extends zzi$zzb
{
    final /* synthetic */ ConnectionResult zzZU;
    final /* synthetic */ zzg$zze zzZV;
    
    zzg$zze$1(final zzg$zze zzZV, final zzj zzj, final ConnectionResult zzZU) {
        this.zzZV = zzZV;
        this.zzZU = zzZU;
        super(zzj);
    }
    
    public void zznn() {
        this.zzZV.zzZK.zzf(this.zzZU);
    }
}
