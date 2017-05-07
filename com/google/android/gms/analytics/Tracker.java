// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.app.Activity;
import java.util.TimerTask;
import java.util.Timer;
import com.google.android.gms.internal.fq;
import java.util.Locale;
import android.text.TextUtils;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class Tracker
{
    private final TrackerHandler vM;
    private final Map<String, String> vN;
    private ad vO;
    private final h vP;
    private final ae vQ;
    private final g vR;
    private boolean vS;
    private a vT;
    private aj vU;
    
    Tracker(final String s, final TrackerHandler trackerHandler) {
        this(s, trackerHandler, h.cb(), ae.cZ(), g.ca(), new z("tracking"));
    }
    
    Tracker(final String s, final TrackerHandler vm, final h vp, final ae vq, final g vr, final ad vo) {
        this.vN = new HashMap<String, String>();
        this.vM = vm;
        if (s != null) {
            this.vN.put("&tid", s);
        }
        this.vN.put("useSecure", "1");
        this.vP = vp;
        this.vQ = vq;
        this.vR = vr;
        this.vO = vo;
        this.vT = new a();
    }
    
    void a(final Context context, final aj vu) {
        aa.y("Loading Tracker config values.");
        this.vU = vu;
        if (this.vU.dj()) {
            final String dk = this.vU.dk();
            this.set("&tid", dk);
            aa.y("[Tracker] trackingId loaded: " + dk);
        }
        if (this.vU.dl()) {
            final String string = Double.toString(this.vU.dm());
            this.set("&sf", string);
            aa.y("[Tracker] sample frequency loaded: " + string);
        }
        if (this.vU.dn()) {
            this.setSessionTimeout(this.vU.getSessionTimeout());
            aa.y("[Tracker] session timeout loaded: " + this.dc());
        }
        if (this.vU.do()) {
            this.enableAutoActivityTracking(this.vU.dp());
            aa.y("[Tracker] auto activity tracking loaded: " + this.dd());
        }
        if (this.vU.dq()) {
            if (this.vU.dr()) {
                this.set("&aip", "1");
                aa.y("[Tracker] anonymize ip loaded: true");
            }
            aa.y("[Tracker] anonymize ip loaded: false");
        }
        this.vS = this.vU.ds();
        if (this.vU.ds()) {
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), context));
            aa.y("[Tracker] report uncaught exceptions loaded: " + this.vS);
        }
    }
    
    long dc() {
        return this.vT.dc();
    }
    
    boolean dd() {
        return this.vT.dd();
    }
    
    public void enableAdvertisingIdCollection(final boolean b) {
        if (!b) {
            this.vN.put("&ate", null);
            this.vN.put("&adid", null);
        }
        else {
            if (this.vN.containsKey("&ate")) {
                this.vN.remove("&ate");
            }
            if (this.vN.containsKey("&adid")) {
                this.vN.remove("&adid");
            }
        }
    }
    
    public void enableAutoActivityTracking(final boolean b) {
        this.vT.enableAutoActivityTracking(b);
    }
    
    public String get(final String s) {
        u.cy().a(u.a.tR);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (this.vN.containsKey(s)) {
                return this.vN.get(s);
            }
            if (s.equals("&ul")) {
                return ak.a(Locale.getDefault());
            }
            if (this.vP != null && this.vP.C(s)) {
                return this.vP.getValue(s);
            }
            if (this.vQ != null && this.vQ.C(s)) {
                return this.vQ.getValue(s);
            }
            if (this.vR != null && this.vR.C(s)) {
                return this.vR.getValue(s);
            }
        }
        return null;
    }
    
    public void send(final Map<String, String> map) {
        u.cy().a(u.a.tT);
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.putAll(this.vN);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (TextUtils.isEmpty((CharSequence)hashMap.get("&tid"))) {
            aa.z(String.format("Missing tracking id (%s) parameter.", "&tid"));
        }
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = hashMap.get("&t")))) {
            aa.z(String.format("Missing hit type (%s) parameter.", "&t"));
            s = "";
        }
        if (this.vT.de()) {
            hashMap.put("&sc", "start");
        }
        if (!s.equals("transaction") && !s.equals("item") && !this.vO.cS()) {
            aa.z("Too many hits sent too quickly, rate limiting invoked.");
            return;
        }
        this.vM.q((Map<String, String>)hashMap);
    }
    
    public void set(final String s, final String s2) {
        fq.b(s, (Object)"Key should be non-null");
        u.cy().a(u.a.tS);
        this.vN.put(s, s2);
    }
    
    public void setAnonymizeIp(final boolean b) {
        this.set("&aip", ak.u(b));
    }
    
    public void setAppId(final String s) {
        this.set("&aid", s);
    }
    
    public void setAppInstallerId(final String s) {
        this.set("&aiid", s);
    }
    
    public void setAppName(final String s) {
        this.set("&an", s);
    }
    
    public void setAppVersion(final String s) {
        this.set("&av", s);
    }
    
    public void setClientId(final String s) {
        this.set("&cid", s);
    }
    
    public void setEncoding(final String s) {
        this.set("&de", s);
    }
    
    public void setHostname(final String s) {
        this.set("&dh", s);
    }
    
    public void setLanguage(final String s) {
        this.set("&ul", s);
    }
    
    public void setLocation(final String s) {
        this.set("&dl", s);
    }
    
    public void setPage(final String s) {
        this.set("&dp", s);
    }
    
    public void setReferrer(final String s) {
        this.set("&dr", s);
    }
    
    public void setSampleRate(final double n) {
        this.set("&sf", Double.toHexString(n));
    }
    
    public void setScreenColors(final String s) {
        this.set("&sd", s);
    }
    
    public void setScreenName(final String s) {
        this.set("&cd", s);
    }
    
    public void setScreenResolution(final int n, final int n2) {
        if (n < 0 && n2 < 0) {
            aa.z("Invalid width or height. The values should be non-negative.");
            return;
        }
        this.set("&sr", n + "x" + n2);
    }
    
    public void setSessionTimeout(final long n) {
        this.vT.setSessionTimeout(1000L * n);
    }
    
    public void setTitle(final String s) {
        this.set("&dt", s);
    }
    
    public void setUseSecure(final boolean b) {
        this.set("useSecure", ak.u(b));
    }
    
    public void setViewportSize(final String s) {
        this.set("&vp", s);
    }
    
    private class a implements GoogleAnalytics.a
    {
        private i tg;
        private Timer vV;
        private TimerTask vW;
        private boolean vX;
        private boolean vY;
        private int vZ;
        private long wa;
        private boolean wb;
        private long wc;
        
        public a() {
            this.vX = false;
            this.vY = false;
            this.vZ = 0;
            this.wa = -1L;
            this.wb = false;
            this.tg = new i() {
                @Override
                public long currentTimeMillis() {
                    return System.currentTimeMillis();
                }
            };
        }
        
        private void df() {
            final GoogleAnalytics cm = GoogleAnalytics.cM();
            if (cm == null) {
                aa.w("GoogleAnalytics isn't initialized for the Tracker!");
                return;
            }
            if (this.wa >= 0L || this.vY) {
                cm.a((GoogleAnalytics.a)Tracker.this.vT);
                return;
            }
            cm.b((GoogleAnalytics.a)Tracker.this.vT);
        }
        
        private void dg() {
            synchronized (this) {
                if (this.vV != null) {
                    this.vV.cancel();
                    this.vV = null;
                }
            }
        }
        
        public long dc() {
            return this.wa;
        }
        
        public boolean dd() {
            return this.vY;
        }
        
        public boolean de() {
            final boolean wb = this.wb;
            this.wb = false;
            return wb;
        }
        
        boolean dh() {
            return this.wa == 0L || (this.wa > 0L && this.tg.currentTimeMillis() > this.wc + this.wa);
        }
        
        public void enableAutoActivityTracking(final boolean vy) {
            this.vY = vy;
            this.df();
        }
        
        @Override
        public void f(final Activity activity) {
            u.cy().a(u.a.uQ);
            this.dg();
            if (!this.vX && this.vZ == 0 && this.dh()) {
                this.wb = true;
            }
            this.vX = true;
            ++this.vZ;
            if (this.vY) {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("&t", "appview");
                u.cy().t(true);
                final Tracker wd = Tracker.this;
                String s;
                if (Tracker.this.vU != null) {
                    s = Tracker.this.vU.h(activity);
                }
                else {
                    s = activity.getClass().getCanonicalName();
                }
                wd.set("&cd", s);
                Tracker.this.send(hashMap);
                u.cy().t(false);
            }
        }
        
        @Override
        public void g(final Activity activity) {
            u.cy().a(u.a.uR);
            --this.vZ;
            this.vZ = Math.max(0, this.vZ);
            this.wc = this.tg.currentTimeMillis();
            if (this.vZ == 0) {
                this.dg();
                this.vW = new Tracker.a.a();
                (this.vV = new Timer("waitForActivityStart")).schedule(this.vW, 1000L);
            }
        }
        
        public void setSessionTimeout(final long wa) {
            this.wa = wa;
            this.df();
        }
        
        private class a extends TimerTask
        {
            @Override
            public void run() {
                Tracker.a.this.vX = false;
            }
        }
    }
}
