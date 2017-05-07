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
import android.net.Uri;
import android.content.Intent;
import android.content.Context;
import java.lang.ref.WeakReference;
import android.content.BroadcastReceiver;

class zzg$zzb extends BroadcastReceiver
{
    private WeakReference<zzg> zzXJ;
    
    zzg$zzb(final zzg zzg) {
        this.zzXJ = new WeakReference<zzg>(zzg);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        String schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if (schemeSpecificPart != null && schemeSpecificPart.equals("com.google.android.gms")) {
            final zzg zzg = this.zzXJ.get();
            if (zzg != null) {
                zzg.resume();
            }
        }
    }
}
