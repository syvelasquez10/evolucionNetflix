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
import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.content.BroadcastReceiver;

class zzi$zzc extends BroadcastReceiver
{
    private WeakReference<zzi> zzaax;
    
    zzi$zzc(final zzi zzi) {
        this.zzaax = new WeakReference<zzi>(zzi);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        String schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if (schemeSpecificPart != null && schemeSpecificPart.equals("com.google.android.gms")) {
            final zzi zzi = this.zzaax.get();
            if (zzi != null) {
                zzi.resume();
            }
        }
    }
}
