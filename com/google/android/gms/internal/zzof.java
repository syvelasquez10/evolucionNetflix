// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import com.google.android.gms.analytics.internal.zzam;
import java.util.Locale;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.text.TextUtils;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.Iterator;
import android.net.Uri;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.android.gms.common.internal.zzx;
import java.util.List;
import android.content.Context;

public final class zzof
{
    private static volatile zzof zzaId;
    private final Context mContext;
    private volatile zzok zzMm;
    private final List<zzog> zzaIe;
    private final zzoa zzaIf;
    private final zzof$zza zzaIg;
    private Thread.UncaughtExceptionHandler zzaIh;
    
    zzof(Context applicationContext) {
        applicationContext = applicationContext.getApplicationContext();
        zzx.zzv(applicationContext);
        this.mContext = applicationContext;
        this.zzaIg = new zzof$zza(this);
        this.zzaIe = new CopyOnWriteArrayList<zzog>();
        this.zzaIf = new zzoa();
    }
    
    public static zzof zzaI(final Context context) {
        zzx.zzv(context);
        Label_0034: {
            if (zzof.zzaId != null) {
                break Label_0034;
            }
            synchronized (zzof.class) {
                if (zzof.zzaId == null) {
                    zzof.zzaId = new zzof(context);
                }
                return zzof.zzaId;
            }
        }
    }
    
    private void zzb(final zzob zzob) {
        zzx.zzci("deliver should be called from worker thread");
        zzx.zzb(zzob.zzxm(), "Measurement must be submitted");
        final List<zzoh> zzxj = zzob.zzxj();
        if (!zzxj.isEmpty()) {
            final HashSet<Uri> set = new HashSet<Uri>();
            for (final zzoh zzoh : zzxj) {
                final Uri zzhs = zzoh.zzhs();
                if (!set.contains(zzhs)) {
                    set.add(zzhs);
                    zzoh.zzb(zzob);
                }
            }
        }
    }
    
    public static void zzic() {
        if (!(Thread.currentThread() instanceof zzof$zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public void zza(final Thread.UncaughtExceptionHandler zzaIh) {
        this.zzaIh = zzaIh;
    }
    
    public <V> Future<V> zzb(final Callable<V> callable) {
        zzx.zzv(callable);
        if (Thread.currentThread() instanceof zzof$zzc) {
            final FutureTask<V> futureTask = new FutureTask<V>(callable);
            futureTask.run();
            return futureTask;
        }
        return this.zzaIg.submit(callable);
    }
    
    void zze(zzob zzxh) {
        if (zzxh.zzxq()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        }
        if (zzxh.zzxm()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
        zzxh = zzxh.zzxh();
        zzxh.zzxn();
        this.zzaIg.execute(new zzof$1(this, zzxh));
    }
    
    public void zzf(final Runnable runnable) {
        zzx.zzv(runnable);
        this.zzaIg.submit(runnable);
    }
    
    public zzok zzxu() {
        Label_0156: {
            if (this.zzMm != null) {
                break Label_0156;
            }
            synchronized (this) {
                Label_0154: {
                    if (this.zzMm != null) {
                        break Label_0154;
                    }
                    final zzok zzMm = new zzok();
                    final PackageManager packageManager = this.mContext.getPackageManager();
                    final String packageName = this.mContext.getPackageName();
                    zzMm.setAppId(packageName);
                    zzMm.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    final String s = null;
                    String s2 = packageName;
                    try {
                        final PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        String versionName = s;
                        String string = packageName;
                        if (packageInfo != null) {
                            s2 = packageName;
                            final CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            string = packageName;
                            s2 = packageName;
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                s2 = packageName;
                                string = applicationLabel.toString();
                            }
                            s2 = string;
                            versionName = packageInfo.versionName;
                        }
                        zzMm.setAppName(string);
                        zzMm.setAppVersion(versionName);
                        this.zzMm = zzMm;
                        // monitorexit(this)
                        return this.zzMm;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        Log.e("GAv4", "Error retrieving package info: appName set to " + s2);
                        final String versionName = s;
                        final String string = s2;
                    }
                }
            }
        }
    }
    
    public zzom zzxv() {
        final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        final zzom zzom = new zzom();
        zzom.setLanguage(zzam.zza(Locale.getDefault()));
        zzom.zzhO(displayMetrics.widthPixels);
        zzom.zzhP(displayMetrics.heightPixels);
        return zzom;
    }
}
