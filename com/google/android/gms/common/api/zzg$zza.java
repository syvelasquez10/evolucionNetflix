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
import com.google.android.gms.common.internal.zze;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzg$zza extends Handler
{
    final /* synthetic */ zzg zzXD;
    
    zzg$zza(final zzg zzXD, final Looper looper) {
        this.zzXD = zzXD;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
            case 1: {
                this.zzXD.zzmN();
            }
            case 2: {
                this.zzXD.resume();
            }
        }
    }
}
