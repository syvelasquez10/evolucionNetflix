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
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
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
import com.google.android.gms.common.internal.ResolveAccountResponse;

class zzg$zzb$1 extends zzi$zzb
{
    final /* synthetic */ zzg zzZM;
    final /* synthetic */ ResolveAccountResponse zzZP;
    final /* synthetic */ zzg$zzb zzZQ;
    
    zzg$zzb$1(final zzg$zzb zzZQ, final zzj zzj, final zzg zzZM, final ResolveAccountResponse zzZP) {
        this.zzZQ = zzZQ;
        this.zzZM = zzZM;
        this.zzZP = zzZP;
        super(zzj);
    }
    
    public void zznn() {
        this.zzZM.zza(this.zzZP);
    }
}
