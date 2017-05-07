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
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzli$zza extends Handler
{
    final /* synthetic */ zzli zzacr;
    
    zzli$zza(final zzli zzacr, final Looper looper) {
        this.zzacr = zzacr;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
            case 1: {
                this.zzacr.zzod();
            }
            case 2: {
                this.zzacr.resume();
            }
            case 3: {
                ((zzli$zzb)message.obj).zzg(this.zzacr);
            }
            case 4: {
                throw (RuntimeException)message.obj;
            }
        }
    }
}
