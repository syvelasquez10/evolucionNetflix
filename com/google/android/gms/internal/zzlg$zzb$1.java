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

class zzlg$zzb$1 extends zzli$zzb
{
    final /* synthetic */ zzlg zzabN;
    final /* synthetic */ ResolveAccountResponse zzabQ;
    final /* synthetic */ zzlg$zzb zzabR;
    
    zzlg$zzb$1(final zzlg$zzb zzabR, final zzlj zzlj, final zzlg zzabN, final ResolveAccountResponse zzabQ) {
        this.zzabR = zzabR;
        this.zzabN = zzabN;
        this.zzabQ = zzabQ;
        super(zzlj);
    }
    
    public void zznO() {
        this.zzabN.zza(this.zzabQ);
    }
}
