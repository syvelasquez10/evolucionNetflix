// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Locale;
import java.util.Map;
import java.util.Iterator;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.os.Build$VERSION;
import android.app.Application;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.HashSet;
import android.content.Context;
import java.util.Set;

public class GoogleAnalytics extends TrackerHandler
{
    private static GoogleAnalytics AC;
    private static boolean Av;
    private Set<GoogleAnalytics$a> AA;
    private boolean AB;
    private boolean Aw;
    private ae Ax;
    private volatile Boolean Ay;
    private Logger Az;
    private Context mContext;
    private String xL;
    private String xM;
    private f ye;
    
    protected GoogleAnalytics(final Context context) {
        this(context, s.B(context), q.ea());
    }
    
    private GoogleAnalytics(final Context context, final f ye, final ae ax) {
        this.Ay = false;
        this.AB = false;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.ye = ye;
        this.Ax = ax;
        g.y(this.mContext);
        ad.y(this.mContext);
        h.y(this.mContext);
        this.Az = new k();
        this.AA = new HashSet<GoogleAnalytics$a>();
        this.eF();
    }
    
    private Tracker a(final Tracker tracker) {
        if (this.xL != null) {
            tracker.set("&an", this.xL);
        }
        if (this.xM != null) {
            tracker.set("&av", this.xM);
        }
        return tracker;
    }
    
    private int ai(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        if ("error".equals(lowerCase)) {
            return 3;
        }
        return -1;
    }
    
    static GoogleAnalytics eE() {
        synchronized (GoogleAnalytics.class) {
            return GoogleAnalytics.AC;
        }
    }
    
    private void eF() {
        if (!GoogleAnalytics.Av) {
            ApplicationInfo applicationInfo;
            while (true) {
                try {
                    applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
                    if (applicationInfo == null) {
                        z.W("Couldn't get ApplicationInfo to load gloabl config.");
                        return;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    z.V("PackageManager doesn't know about package: " + ex);
                    applicationInfo = null;
                    continue;
                }
                break;
            }
            final Bundle metaData = applicationInfo.metaData;
            if (metaData != null) {
                final int int1 = metaData.getInt("com.google.android.gms.analytics.globalConfigResource");
                if (int1 > 0) {
                    final v v = new u(this.mContext).w(int1);
                    if (v != null) {
                        this.a(v);
                    }
                }
            }
        }
    }
    
    public static GoogleAnalytics getInstance(final Context context) {
        synchronized (GoogleAnalytics.class) {
            if (GoogleAnalytics.AC == null) {
                GoogleAnalytics.AC = new GoogleAnalytics(context);
            }
            return GoogleAnalytics.AC;
        }
    }
    
    void a(final GoogleAnalytics$a googleAnalytics$a) {
        this.AA.add(googleAnalytics$a);
        if (this.mContext instanceof Application) {
            this.enableAutoActivityReports((Application)this.mContext);
        }
    }
    
    void a(final v v) {
        z.V("Loading global config values.");
        if (v.eu()) {
            this.xL = v.ev();
            z.V("app name loaded: " + this.xL);
        }
        if (v.ew()) {
            this.xM = v.ex();
            z.V("app version loaded: " + this.xM);
        }
        if (v.ey()) {
            final int ai = this.ai(v.ez());
            if (ai >= 0) {
                z.V("log level loaded: " + ai);
                this.getLogger().setLogLevel(ai);
            }
        }
        if (v.eA()) {
            this.Ax.setLocalDispatchPeriod(v.eB());
        }
        if (v.eC()) {
            this.setDryRun(v.eD());
        }
    }
    
    void b(final GoogleAnalytics$a googleAnalytics$a) {
        this.AA.remove(googleAnalytics$a);
    }
    
    @Deprecated
    public void dispatchLocalHits() {
        this.Ax.dispatchLocalHits();
    }
    
    public void enableAutoActivityReports(final Application application) {
        if (Build$VERSION.SDK_INT >= 14 && !this.AB) {
            application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new GoogleAnalytics$b(this));
            this.AB = true;
        }
    }
    
    void g(final Activity activity) {
        final Iterator<GoogleAnalytics$a> iterator = this.AA.iterator();
        while (iterator.hasNext()) {
            iterator.next().i(activity);
        }
    }
    
    public boolean getAppOptOut() {
        t.eq().a(t$a.zW);
        return this.Ay;
    }
    
    public Logger getLogger() {
        return this.Az;
    }
    
    void h(final Activity activity) {
        final Iterator<GoogleAnalytics$a> iterator = this.AA.iterator();
        while (iterator.hasNext()) {
            iterator.next().j(activity);
        }
    }
    
    public boolean isDryRunEnabled() {
        t.eq().a(t$a.Ai);
        return this.Aw;
    }
    
    public Tracker newTracker(final int n) {
        synchronized (this) {
            t.eq().a(t$a.zS);
            final Tracker tracker = new Tracker(null, this, this.mContext);
            if (n > 0) {
                final ai ai = new ah(this.mContext).w(n);
                if (ai != null) {
                    tracker.a(ai);
                }
            }
            return this.a(tracker);
        }
    }
    
    public Tracker newTracker(final String s) {
        synchronized (this) {
            t.eq().a(t$a.zS);
            return this.a(new Tracker(s, this, this.mContext));
        }
    }
    
    public void reportActivityStart(final Activity activity) {
        if (!this.AB) {
            this.g(activity);
        }
    }
    
    public void reportActivityStop(final Activity activity) {
        if (!this.AB) {
            this.h(activity);
        }
    }
    
    public void setAppOptOut(final boolean b) {
        t.eq().a(t$a.zV);
        this.Ay = b;
        if (this.Ay) {
            this.ye.dI();
        }
    }
    
    public void setDryRun(final boolean aw) {
        t.eq().a(t$a.Ah);
        this.Aw = aw;
    }
    
    @Deprecated
    public void setLocalDispatchPeriod(final int localDispatchPeriod) {
        this.Ax.setLocalDispatchPeriod(localDispatchPeriod);
    }
    
    public void setLogger(final Logger az) {
        t.eq().a(t$a.Aj);
        this.Az = az;
    }
    
    @Override
    void u(final Map<String, String> map) {
        // monitorenter(this)
        if (map == null) {
            try {
                throw new IllegalArgumentException("hit cannot be null");
            }
            finally {
            }
            // monitorexit(this)
        }
        aj.a(map, "&ul", aj.a(Locale.getDefault()));
        aj.a(map, "&sr", ad.eR());
        map.put("&_u", t.eq().es());
        t.eq().er();
        this.ye.u(map);
    }
    // monitorexit(this)
}
