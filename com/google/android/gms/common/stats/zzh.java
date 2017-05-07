// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.text.TextUtils;
import java.util.List;
import android.content.Intent;
import android.os.PowerManager;
import com.google.android.gms.internal.zzlv;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.android.gms.internal.zzll;
import android.content.IntentFilter;
import android.content.ComponentName;

public class zzh
{
    private static String TAG;
    private static Integer zzafB;
    private static final ComponentName zzafz;
    private static zzh zzaga;
    private static IntentFilter zzagb;
    
    static {
        zzh.TAG = "WakeLockTracker";
        zzh.zzaga = new zzh();
        zzafz = new ComponentName("com.google.android.gms", "com.google.android.gms.common.stats.GmsCoreStatsService");
        zzh.zzagb = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    }
    
    private static int getLogLevel() {
        try {
            if (zzll.zzjk()) {
                return zzc$zzb.zzafD.get();
            }
            return zzd.LOG_LEVEL_OFF;
        }
        catch (SecurityException ex) {
            return zzd.LOG_LEVEL_OFF;
        }
    }
    
    private int zzal(final Context context) {
        final boolean b = true;
        final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzh.zzagb);
        int intExtra;
        if (registerReceiver == null) {
            intExtra = 0;
        }
        else {
            intExtra = registerReceiver.getIntExtra("plugged", 0);
        }
        boolean b2;
        if ((intExtra & 0x7) != 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (zzlv.zzpW()) {
            b3 = ((PowerManager)context.getSystemService("power")).isInteractive();
        }
        else {
            b3 = ((PowerManager)context.getSystemService("power")).isScreenOn();
        }
        int n;
        if (b3) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n << 1 | ((b2 && b) ? 1 : 0);
    }
    
    private float zzam(final Context context) {
        final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        float n = Float.NaN;
        if (registerReceiver != null) {
            n = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
        }
        return n;
    }
    
    private static boolean zzan(final Context context) {
        if (zzh.zzafB == null) {
            zzh.zzafB = getLogLevel();
        }
        return zzh.zzafB != zzd.LOG_LEVEL_OFF;
    }
    
    public static zzh zzpL() {
        return zzh.zzaga;
    }
    
    public void zza(final Context context, final String s, final int n, final String s2, final String s3, final int n2, final List<String> list) {
        if (zzan(context)) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                Log.e(zzh.TAG, "missing wakeLock key. " + s);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (7 == n || 8 == n || 10 == n || 11 == n) {
                final WakeLockEvent wakeLockEvent = new WakeLockEvent(currentTimeMillis, n, s2, n2, list, s, SystemClock.elapsedRealtime(), this.zzal(context), s3, context.getPackageName(), this.zzam(context));
                try {
                    context.startService(new Intent().setComponent(zzh.zzafz).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)wakeLockEvent));
                }
                catch (Exception ex) {
                    Log.wtf(zzh.TAG, (Throwable)ex);
                }
            }
        }
    }
}
