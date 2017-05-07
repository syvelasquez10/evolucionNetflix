// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.util.Log;
import com.google.android.gms.analytics.internal.zzy;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import com.google.android.gms.analytics.internal.zzan;
import android.content.Context;
import java.util.HashSet;
import com.google.android.gms.analytics.internal.zzf;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public final class GoogleAnalytics extends zza
{
    private static List<Runnable> zzKt;
    private boolean zzKu;
    private Set<Object> zzKv;
    private boolean zzKx;
    private volatile boolean zzKy;
    private boolean zzKz;
    private boolean zzpr;
    
    static {
        GoogleAnalytics.zzKt = new ArrayList<Runnable>();
    }
    
    public GoogleAnalytics(final zzf zzf) {
        super(zzf);
        this.zzKv = new HashSet<Object>();
    }
    
    public static GoogleAnalytics getInstance(final Context context) {
        return zzf.zzX(context).zzis();
    }
    
    private zzan zzhA() {
        return this.zzhp().zzhA();
    }
    
    public static void zzhx() {
        Label_0054: {
            synchronized (GoogleAnalytics.class) {
                if (GoogleAnalytics.zzKt == null) {
                    break Label_0054;
                }
                final Iterator<Runnable> iterator = GoogleAnalytics.zzKt.iterator();
                while (iterator.hasNext()) {
                    iterator.next().run();
                }
            }
            GoogleAnalytics.zzKt = null;
        }
    }
    // monitorexit(GoogleAnalytics.class)
    
    public boolean getAppOptOut() {
        return this.zzKy;
    }
    
    public String getClientId() {
        zzx.zzci("getClientId can not be called from the main thread");
        return this.zzhp().zziv().zzjd();
    }
    
    @Deprecated
    public Logger getLogger() {
        return zzae.getLogger();
    }
    
    public boolean isDryRunEnabled() {
        return this.zzKx;
    }
    
    public boolean isInitialized() {
        return this.zzpr && !this.zzKu;
    }
    
    public Tracker newTracker(final String s) {
        synchronized (this) {
            final Tracker tracker = new Tracker(this.zzhp(), s, null);
            tracker.zza();
            return tracker;
        }
    }
    
    public void setDryRun(final boolean zzKx) {
        this.zzKx = zzKx;
    }
    
    @Deprecated
    public void setLogger(final Logger logger) {
        zzae.setLogger(logger);
        if (!this.zzKz) {
            Log.i((String)zzy.zzNa.get(), "GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzy.zzNa.get() + " DEBUG");
            this.zzKz = true;
        }
    }
    
    public void zza() {
        this.zzhw();
        this.zzpr = true;
    }
    
    void zzhw() {
        final zzan zzhA = this.zzhA();
        if (zzhA.zzkc()) {
            this.getLogger().setLogLevel(zzhA.getLogLevel());
        }
        if (zzhA.zzkg()) {
            this.setDryRun(zzhA.zzkh());
        }
        if (zzhA.zzkc()) {
            final Logger logger = zzae.getLogger();
            if (logger != null) {
                logger.setLogLevel(zzhA.getLogLevel());
            }
        }
    }
}
