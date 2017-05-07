// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.content.ComponentName;
import android.os.Process;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.util.Log;
import android.os.Parcelable;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Debug;
import com.google.android.gms.internal.zzmy;
import android.content.Context;
import com.google.android.gms.internal.zzmm;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class zzb
{
    private static final Object zzafW;
    private static Integer zzahE;
    private static zzb zzahy;
    private final List<String> zzahA;
    private final List<String> zzahB;
    private final List<String> zzahC;
    private zze zzahD;
    private zze zzahF;
    private final List<String> zzahz;
    
    static {
        zzafW = new Object();
    }
    
    private zzb() {
        if (getLogLevel() == zzd.LOG_LEVEL_OFF) {
            this.zzahz = (List<String>)Collections.EMPTY_LIST;
            this.zzahA = (List<String>)Collections.EMPTY_LIST;
            this.zzahB = (List<String>)Collections.EMPTY_LIST;
            this.zzahC = (List<String>)Collections.EMPTY_LIST;
            return;
        }
        final String s = zzc$zza.zzahI.get();
        List<String> zzahz;
        if (s == null) {
            zzahz = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzahz = Arrays.asList(s.split(","));
        }
        this.zzahz = zzahz;
        final String s2 = zzc$zza.zzahJ.get();
        List<String> zzahA;
        if (s2 == null) {
            zzahA = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzahA = Arrays.asList(s2.split(","));
        }
        this.zzahA = zzahA;
        final String s3 = zzc$zza.zzahK.get();
        List<String> zzahB;
        if (s3 == null) {
            zzahB = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzahB = Arrays.asList(s3.split(","));
        }
        this.zzahB = zzahB;
        final String s4 = zzc$zza.zzahL.get();
        List<String> zzahC;
        if (s4 == null) {
            zzahC = (List<String>)Collections.EMPTY_LIST;
        }
        else {
            zzahC = Arrays.asList(s4.split(","));
        }
        this.zzahC = zzahC;
        this.zzahD = new zze(1024, zzc$zza.zzahM.get());
        this.zzahF = new zze(1024, zzc$zza.zzahM.get());
    }
    
    private static int getLogLevel() {
        Label_0032: {
            if (zzb.zzahE != null) {
                break Label_0032;
            }
            try {
                int n;
                if (zzmm.zzjA()) {
                    n = zzc$zza.zzahH.get();
                }
                else {
                    n = zzd.LOG_LEVEL_OFF;
                }
                zzb.zzahE = n;
                return zzb.zzahE;
            }
            catch (SecurityException ex) {
                zzb.zzahE = zzd.LOG_LEVEL_OFF;
                return zzb.zzahE;
            }
        }
    }
    
    private void zza(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final String s5) {
        final long currentTimeMillis = System.currentTimeMillis();
        String zzl;
        final String s6 = zzl = null;
        if ((getLogLevel() & zzd.zzahR) != 0x0) {
            zzl = s6;
            if (n != 13) {
                zzl = zzmy.zzl(3, 5);
            }
        }
        long nativeHeapAllocatedSize = 0L;
        if ((getLogLevel() & zzd.zzahT) != 0x0) {
            nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
        }
        ConnectionEvent connectionEvent;
        if (n == 1 || n == 4 || n == 14) {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, null, null, null, null, zzl, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        else {
            connectionEvent = new ConnectionEvent(currentTimeMillis, n, s2, s3, s4, s5, zzl, s, SystemClock.elapsedRealtime(), nativeHeapAllocatedSize);
        }
        context.startService(new Intent().setComponent(zzd.zzahN).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)connectionEvent));
    }
    
    private void zza(final Context context, final String s, final String s2, final Intent intent, final int n) {
        final String s3 = null;
        if (this.zzqi() && this.zzahD != null) {
            String name;
            String processName;
            String zzaq;
            if (n == 4 || n == 1) {
                if (!this.zzahD.zzcy(s)) {
                    return;
                }
                name = null;
                processName = null;
                zzaq = s3;
            }
            else {
                final ServiceInfo zzd = zzd(context, intent);
                if (zzd == null) {
                    Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", s2, intent.toUri(0)));
                    return;
                }
                processName = zzd.processName;
                name = zzd.name;
                zzaq = zzmy.zzaq(context);
                if (!this.zzb(zzaq, s2, processName, name)) {
                    return;
                }
                this.zzahD.zzcx(s);
            }
            this.zza(context, s, n, zzaq, s2, processName, name);
        }
    }
    
    private String zzb(final ServiceConnection serviceConnection) {
        return String.valueOf(Process.myPid() << 32 | System.identityHashCode(serviceConnection));
    }
    
    private boolean zzb(final String s, final String s2, final String s3, final String s4) {
        final int logLevel = getLogLevel();
        return !this.zzahz.contains(s) && !this.zzahA.contains(s2) && !this.zzahB.contains(s3) && !this.zzahC.contains(s4) && (!s3.equals(s) || (logLevel & zzd.zzahS) == 0x0);
    }
    
    private boolean zzc(final Context context, final Intent intent) {
        final ComponentName component = intent.getComponent();
        return component != null && (!com.google.android.gms.common.internal.zzd.zzaeK || !"com.google.android.gms".equals(component.getPackageName())) && zzmm.zzl(context, component.getPackageName());
    }
    
    private static ServiceInfo zzd(final Context context, final Intent intent) {
        final List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", intent.toUri(0), zzmy.zzl(3, 20)));
            return null;
        }
        if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", intent.toUri(0), zzmy.zzl(3, 20)));
            final Iterator<ResolveInfo> iterator = queryIntentServices.iterator();
            if (iterator.hasNext()) {
                Log.w("ConnectionTracker", iterator.next().serviceInfo.name);
                return null;
            }
        }
        return queryIntentServices.get(0).serviceInfo;
    }
    
    public static zzb zzqh() {
        synchronized (zzb.zzafW) {
            if (zzb.zzahy == null) {
                zzb.zzahy = new zzb();
            }
            return zzb.zzahy;
        }
    }
    
    private boolean zzqi() {
        return com.google.android.gms.common.internal.zzd.zzaeK && getLogLevel() != zzd.LOG_LEVEL_OFF;
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 1);
    }
    
    public void zza(final Context context, final ServiceConnection serviceConnection, final String s, final Intent intent) {
        this.zza(context, this.zzb(serviceConnection), s, intent, 3);
    }
    
    public boolean zza(final Context context, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        return this.zza(context, context.getClass().getName(), intent, serviceConnection, n);
    }
    
    public boolean zza(final Context context, final String s, final Intent intent, final ServiceConnection serviceConnection, final int n) {
        if (this.zzc(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        final boolean bindService = context.bindService(intent, serviceConnection, n);
        if (bindService) {
            this.zza(context, this.zzb(serviceConnection), s, intent, 2);
        }
        return bindService;
    }
    
    public void zzb(final Context context, final ServiceConnection serviceConnection) {
        this.zza(context, this.zzb(serviceConnection), null, (Intent)null, 4);
    }
}
