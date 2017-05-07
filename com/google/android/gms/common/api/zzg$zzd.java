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
import com.google.android.gms.common.ConnectionResult;
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

public abstract class zzg$zzd implements GoogleApiClient$ConnectionCallbacks
{
    final /* synthetic */ zzg zzXD;
    
    public zzg$zzd(final zzg zzXD) {
        this.zzXD = zzXD;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzXD.zzWK.lock();
        try {
            this.zzXD.zzXw.onConnectionSuspended(n);
        }
        finally {
            this.zzXD.zzWK.unlock();
        }
    }
}
