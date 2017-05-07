// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.os.Process;
import android.util.Log;
import android.content.pm.ServiceInfo;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Debug;
import com.google.android.gms.internal.zzlw;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.Context;
import com.google.android.gms.internal.zzll;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.content.ComponentName;

public class zzb
{
    private static final Object zzadT;
    private static Integer zzafB;
    private static zzb zzafu;
    private static final ComponentName zzafz;
    private zze zzafA;
    private final List<String> zzafv;
    private final List<String> zzafw;
    private final List<String> zzafx;
    private final List<String> zzafy;
    
    static {
        zzadT = new Object();
        zzafz = new ComponentName("com.google.android.gms", "com.google.android.gms.common.stats.GmsCoreStatsService");
    }
    
    private zzb() {
        if (getLogLevel() == zzd.LOG_LEVEL_OFF) {
            this.zzafv = (List<String>)Collections.EMPTY_LIST;
            this.zzafw = (List<String>)Collections.EMPTY_LIST;
            this.zzafx = (List<String>)Collections.EMPTY_LIST;
            this.zzafy = (List<String>)Collections.EMPTY_LIST;
            return;
        }
        final String s = zzc$zza.zzafE.get();
        List<String> zzafv;
        if (s == null) {
            zzafv = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzafv = Arrays.asList(s.split(","));
        }
        this.zzafv = zzafv;
        final String s2 = zzc$zza.zzafF.get();
        List<String> zzafw;
        if (s2 == null) {
            zzafw = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzafw = Arrays.asList(s2.split(","));
        }
        this.zzafw = zzafw;
        final String s3 = zzc$zza.zzafG.get();
        List<String> zzafx;
        if (s3 == null) {
            zzafx = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzafx = Arrays.asList(s3.split(","));
        }
        this.zzafx = zzafx;
        final String s4 = zzc$zza.zzafH.get();
        List<String> zzafy;
        if (s4 == null) {
            zzafy = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzafy = Arrays.asList(s4.split(","));
        }
        this.zzafy = zzafy;
        this.zzafA = new zze(1024, zzc$zza.zzafI.get());
    }
    
    private static int getLogLevel() {
        Label_0032: {
            if (zzb.zzafB != null) {
                break Label_0032;
            }
            try {
                int n;
                if (zzll.zzjk()) {
                    n = zzc$zza.zzafD.get();
                }
                else {
                    n = zzd.LOG_LEVEL_OFF;
                }
                zzb.zzafB = n;
                return zzb.zzafB;
            }
            catch (SecurityException ex) {
                zzb.zzafB = zzd.LOG_LEVEL_OFF;
                return zzb.zzafB;
            }
        }
    }
    
    private void zza(final Context context, final ServiceConnection serviceConnection, final String s, final Intent intent, final int n) {
        if (com.google.android.gms.common.internal.zzd.zzacF) {
            final String zzb = this.zzb(serviceConnection);
            if (this.zza(context, s, intent, zzb, n)) {
                final long currentTimeMillis = System.currentTimeMillis();
                String zzm = null;
                if ((getLogLevel() & zzd.zzafM) != 0x0) {
                    zzm = zzlw.zzm(3, 5);
                }
                long nativeHeapAllocatedSize = 0L;
                if ((getLogLevel() & zzd.zzafO) != 0x0) {
                    nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
                }
                ConnectionEvent connectionEvent;
                if (n == 1 || n == 4) {
                    connectionEvent = new ConnectionEvent(currentTimeMillis, n, null, null, null, null, zzm, zzb, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
                }
                else {
                    final ServiceInfo zzc = zzc(context, intent);
                    connectionEvent = new ConnectionEvent(currentTimeMillis, n, zzlw.zzap(context), s, zzc.processName, zzc.name, zzm, zzb, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
                }
                context.startService(new Intent().setComponent(com.google.android.gms.common.stats.zzb.zzafz).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)connectionEvent));
            }
        }
    }
    
    private boolean zza(final Context context, final String s, final Intent intent, final String s2, final int n) {
        final int logLevel = getLogLevel();
        if (logLevel != zzd.LOG_LEVEL_OFF && this.zzafA != null) {
            if (n == 4 || n == 1) {
                if (this.zzafA.zzcz(s2)) {
                    return true;
                }
            }
            else {
                final ServiceInfo zzc = zzc(context, intent);
                if (zzc == null) {
                    Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", s, intent.toUri(0)));
                    return false;
                }
                final String zzap = zzlw.zzap(context);
                final String processName = zzc.processName;
                final String name = zzc.name;
                if (!this.zzafv.contains(zzap) && !this.zzafw.contains(s) && !this.zzafx.contains(processName) && !this.zzafy.contains(name) && (!processName.equals(zzap) || (logLevel & zzd.zzafN) == 0x0)) {
                    this.zzafA.zzcy(s2);
                    return true;
                }
            }
        }
        return false;
    }
    
    private String zzb(final ServiceConnection serviceConnection) {
        return String.valueOf(Process.myPid() << 32 | System.identityHashCode(serviceConnection));
    }
    
    private boolean zzb(final Context context, final Intent intent) {
        final ComponentName component = intent.getComponent();
        return component != null && (!com.google.android.gms.common.internal.zzd.zzacF || !"com.google.android.gms".equals(component.getPackageName())) && zzll.zzi(context, component.getPackageName());
    }
    
    private static ServiceInfo zzc(final Context context, final Intent intent) {
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", intent.toUri(0), zzlw.zzm(3, 20)));
            return null;
        }
        if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", intent.toUri(0), zzlw.zzm(3, 20)));
            final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
            if (iterator.hasNext()) {
                Log.w("ConnectionTracker", iterator.next().serviceInfo.name);
                return null;
            }
        }
        return queryIntentServices.get(0).serviceInfo;
    }
    
    public static zzb zzpD() {
        synchronized (zzb.zzadT) {
            if (zzb.zzafu == null) {
                zzb.zzafu = new zzb();
            }
            return zzb.zzafu;
        }
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
        this.zza(context, serviceConnection, null, null, 1);
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection, final String s, final Intent intent) {
        this.zza(context, serviceConnection, s, intent, 3);
    }
    
    public boolean zza(final Context context, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        return this.zza(context, context.getClass().getName(), intent, serviceConnection, n);
    }
    
    public boolean zza(final Context context, final String s, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        if (this.zzb(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        final boolean bindService = context.bindService(intent, serviceConnection, n);
        if (bindService) {
            this.zza(context, serviceConnection, s, intent, 2);
        }
        return bindService;
    }
    
    public void zzb(final Context context, final ServiceConnection serviceConnection) {
        this.zza(context, serviceConnection, null, null, 4);
    }
}
