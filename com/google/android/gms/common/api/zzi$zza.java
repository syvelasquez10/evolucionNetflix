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
import com.google.android.gms.common.ConnectionResult;
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
import android.content.Context;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzi$zza extends Handler
{
    final /* synthetic */ zzi zzaap;
    
    zzi$zza(final zzi zzaap, final Looper looper) {
        this.zzaap = zzaap;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
            case 1: {
                this.zzaap.zznC();
            }
            case 2: {
                this.zzaap.resume();
            }
            case 3: {
                ((zzi$zzb)message.obj).zzf(this.zzaap);
            }
            case 4: {
                throw (RuntimeException)message.obj;
            }
        }
    }
}
