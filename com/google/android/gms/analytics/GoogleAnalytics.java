// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Locale;
import java.util.Map;
import android.app.Application$ActivityLifecycleCallbacks;
import android.os.Build$VERSION;
import android.app.Application;
import java.util.Iterator;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.Activity;
import java.util.HashSet;
import java.util.Set;
import android.content.Context;

public class GoogleAnalytics extends TrackerHandler
{
    private static boolean uY;
    private static GoogleAnalytics vf;
    private Context mContext;
    private f sH;
    private String so;
    private String sp;
    private boolean uZ;
    private af va;
    private volatile Boolean vb;
    private Logger vc;
    private Set<a> vd;
    private boolean ve;
    
    protected GoogleAnalytics(final Context context) {
        this(context, t.q(context), r.ci());
    }
    
    private GoogleAnalytics(final Context context, final f sh, final af va) {
        this.vb = false;
        this.ve = false;
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.sH = sh;
        this.va = va;
        g.n(this.mContext);
        ae.n(this.mContext);
        h.n(this.mContext);
        this.vc = new l();
        this.vd = new HashSet<a>();
        this.cN();
    }
    
    private int I(String lowerCase) {
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
    
    private Tracker a(final Tracker tracker) {
        if (this.so != null) {
            tracker.set("&an", this.so);
        }
        if (this.sp != null) {
            tracker.set("&av", this.sp);
        }
        return tracker;
    }
    
    static GoogleAnalytics cM() {
        synchronized (GoogleAnalytics.class) {
            return GoogleAnalytics.vf;
        }
    }
    
    private void cN() {
        if (!GoogleAnalytics.uY) {
            ApplicationInfo applicationInfo;
            while (true) {
                try {
                    applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 129);
                    if (applicationInfo == null) {
                        aa.z("Couldn't get ApplicationInfo to load gloabl config.");
                        return;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    aa.y("PackageManager doesn't know about package: " + ex);
                    applicationInfo = null;
                    continue;
                }
                break;
            }
            final Bundle metaData = applicationInfo.metaData;
            if (metaData != null) {
                final int int1 = metaData.getInt("com.google.android.gms.analytics.globalConfigResource");
                if (int1 > 0) {
                    final w w = new v(this.mContext).p(int1);
                    if (w != null) {
                        this.a(w);
                    }
                }
            }
        }
    }
    
    private void d(final Activity activity) {
        final Iterator<a> iterator = this.vd.iterator();
        while (iterator.hasNext()) {
            iterator.next().f(activity);
        }
    }
    
    private void e(final Activity activity) {
        final Iterator<a> iterator = this.vd.iterator();
        while (iterator.hasNext()) {
            iterator.next().g(activity);
        }
    }
    
    public static GoogleAnalytics getInstance(final Context context) {
        synchronized (GoogleAnalytics.class) {
            if (GoogleAnalytics.vf == null) {
                GoogleAnalytics.vf = new GoogleAnalytics(context);
            }
            return GoogleAnalytics.vf;
        }
    }
    
    void a(final a a) {
        this.vd.add(a);
    }
    
    void a(final w w) {
        aa.y("Loading global config values.");
        if (w.cC()) {
            this.so = w.cD();
            aa.y("app name loaded: " + this.so);
        }
        if (w.cE()) {
            this.sp = w.cF();
            aa.y("app version loaded: " + this.sp);
        }
        if (w.cG()) {
            final int i = this.I(w.cH());
            if (i >= 0) {
                aa.y("log level loaded: " + i);
                this.getLogger().setLogLevel(i);
            }
        }
        if (w.cI()) {
            this.va.setLocalDispatchPeriod(w.cJ());
        }
        if (w.cK()) {
            this.setDryRun(w.cL());
        }
    }
    
    void b(final a a) {
        this.vd.remove(a);
    }
    
    @Deprecated
    public void dispatchLocalHits() {
        this.va.dispatchLocalHits();
    }
    
    public void enableAutoActivityReports(final Application application) {
        if (Build$VERSION.SDK_INT >= 14 && !this.ve) {
            application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new b());
            this.ve = true;
        }
    }
    
    public boolean getAppOptOut() {
        u.cy().a(u.a.uz);
        return this.vb;
    }
    
    public Logger getLogger() {
        return this.vc;
    }
    
    public boolean isDryRunEnabled() {
        u.cy().a(u.a.uL);
        return this.uZ;
    }
    
    public Tracker newTracker(final int n) {
        synchronized (this) {
            u.cy().a(u.a.uv);
            final Tracker tracker = new Tracker(null, this);
            if (n > 0) {
                final aj aj = new ai(this.mContext).p(n);
                if (aj != null) {
                    tracker.a(this.mContext, aj);
                }
            }
            return this.a(tracker);
        }
    }
    
    public Tracker newTracker(final String s) {
        synchronized (this) {
            u.cy().a(u.a.uv);
            return this.a(new Tracker(s, this));
        }
    }
    
    @Override
    void q(final Map<String, String> map) {
        // monitorenter(this)
        if (map == null) {
            try {
                throw new IllegalArgumentException("hit cannot be null");
            }
            finally {
            }
            // monitorexit(this)
        }
        ak.a(map, "&ul", ak.a(Locale.getDefault()));
        ak.a(map, "&sr", ae.cZ().getValue("&sr"));
        map.put("&_u", u.cy().cA());
        u.cy().cz();
        this.sH.q(map);
    }
    // monitorexit(this)
    
    public void reportActivityStart(final Activity activity) {
        if (!this.ve) {
            this.d(activity);
        }
    }
    
    public void reportActivityStop(final Activity activity) {
        if (!this.ve) {
            this.e(activity);
        }
    }
    
    public void setAppOptOut(final boolean b) {
        u.cy().a(u.a.uy);
        this.vb = b;
        if (this.vb) {
            this.sH.bR();
        }
    }
    
    public void setDryRun(final boolean uz) {
        u.cy().a(u.a.uK);
        this.uZ = uz;
    }
    
    @Deprecated
    public void setLocalDispatchPeriod(final int localDispatchPeriod) {
        this.va.setLocalDispatchPeriod(localDispatchPeriod);
    }
    
    public void setLogger(final Logger vc) {
        u.cy().a(u.a.uM);
        this.vc = vc;
    }
    
    interface a
    {
        void f(final Activity p0);
        
        void g(final Activity p0);
    }
    
    class b implements Application$ActivityLifecycleCallbacks
    {
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
        }
        
        public void onActivityResumed(final Activity activity) {
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            GoogleAnalytics.this.d(activity);
        }
        
        public void onActivityStopped(final Activity activity) {
            GoogleAnalytics.this.e(activity);
        }
    }
}
