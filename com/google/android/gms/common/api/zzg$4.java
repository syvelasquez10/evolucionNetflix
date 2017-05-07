// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.IntentFilter;
import com.google.android.gms.common.internal.zzu;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zze$zza;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Set;
import android.content.BroadcastReceiver;
import java.util.Queue;
import com.google.android.gms.common.internal.zzj;
import java.util.concurrent.locks.Condition;
import java.util.Map;
import com.google.android.gms.common.internal.zzj$zza;
import android.os.Looper;
import com.google.android.gms.common.internal.zze;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;

class zzg$4 implements GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ zzg zzXD;
    final /* synthetic */ Api zzXE;
    final /* synthetic */ int zzXF;
    
    zzg$4(final zzg zzXD, final Api zzXE, final int zzXF) {
        this.zzXD = zzXD;
        this.zzXE = zzXE;
        this.zzXF = zzXF;
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzXD.zzXw.zza(connectionResult, this.zzXE, this.zzXF);
    }
}
