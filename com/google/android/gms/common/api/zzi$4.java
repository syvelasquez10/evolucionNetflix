// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.IntentFilter;
import com.google.android.gms.common.internal.zzx;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzk$zza;
import java.util.Set;
import android.content.BroadcastReceiver;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.signin.zzd;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.Queue;
import com.google.android.gms.common.internal.zzk;
import java.util.concurrent.locks.Condition;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;

class zzi$4 implements GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ zzi zzaap;
    final /* synthetic */ Api zzaaq;
    final /* synthetic */ int zzaar;
    
    zzi$4(final zzi zzaap, final Api zzaaq, final int zzaar) {
        this.zzaap = zzaap;
        this.zzaaq = zzaaq;
        this.zzaar = zzaar;
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzaap.zzZs.lock();
        try {
            this.zzaap.zzaai.zza(connectionResult, this.zzaaq, this.zzaar);
        }
        finally {
            this.zzaap.zzZs.unlock();
        }
    }
}
