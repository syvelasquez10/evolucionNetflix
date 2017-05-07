// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.Writer;
import java.io.StringWriter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzx;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.RemoteException;
import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import com.google.android.gms.common.api.Api$zzd;
import com.google.android.gms.common.api.Api$zze;
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
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.internal.zzk$zza;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.Api$zzc;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.common.internal.zzk;
import java.util.concurrent.locks.Condition;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

class zzli$1 implements zzli$zze
{
    final /* synthetic */ zzli zzacr;
    
    zzli$1(final zzli zzacr) {
        this.zzacr = zzacr;
    }
    
    @Override
    public void zzc(final zzli$zzf<?> zzli$zzf) {
        this.zzacr.zzacm.remove(zzli$zzf);
        if (zzli$zzf.zznF() != null && this.zzacr.zzacn != null) {
            this.zzacr.zzacn.remove(zzli$zzf.zznF());
        }
    }
}
