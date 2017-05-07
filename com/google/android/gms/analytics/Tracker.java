// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.n;
import java.util.Locale;
import android.text.TextUtils;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class Tracker
{
    private final TrackerHandler Bm;
    private ac Bn;
    private final h Bo;
    private final ad Bp;
    private final g Bq;
    private boolean Br;
    private Tracker$a Bs;
    private ai Bt;
    private ExceptionReporter Bu;
    private Context mContext;
    private final Map<String, String> qM;
    
    Tracker(final String s, final TrackerHandler trackerHandler, final Context context) {
        this(s, trackerHandler, h.dR(), ad.eR(), g.dQ(), new y("tracking"), context);
    }
    
    Tracker(final String s, final TrackerHandler bm, final h bo, final ad bp, final g bq, final ac bn, final Context context) {
        this.qM = new HashMap<String, String>();
        this.Bm = bm;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
        if (s != null) {
            this.qM.put("&tid", s);
        }
        this.qM.put("useSecure", "1");
        this.Bo = bo;
        this.Bp = bp;
        this.Bq = bq;
        this.qM.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        this.Bn = bn;
        this.Bs = new Tracker$a(this);
        this.enableAdvertisingIdCollection(false);
    }
    
    void a(final ai bt) {
        z.V("Loading Tracker config values.");
        this.Bt = bt;
        if (this.Bt.fa()) {
            final String fb = this.Bt.fb();
            this.set("&tid", fb);
            z.V("[Tracker] trackingId loaded: " + fb);
        }
        if (this.Bt.fc()) {
            final String string = Double.toString(this.Bt.fd());
            this.set("&sf", string);
            z.V("[Tracker] sample frequency loaded: " + string);
        }
        if (this.Bt.fe()) {
            this.setSessionTimeout(this.Bt.getSessionTimeout());
            z.V("[Tracker] session timeout loaded: " + this.eU());
        }
        if (this.Bt.ff()) {
            this.enableAutoActivityTracking(this.Bt.fg());
            z.V("[Tracker] auto activity tracking loaded: " + this.eV());
        }
        if (this.Bt.fh()) {
            if (this.Bt.fi()) {
                this.set("&aip", "1");
                z.V("[Tracker] anonymize ip loaded: true");
            }
            z.V("[Tracker] anonymize ip loaded: false");
        }
        this.enableExceptionReporting(this.Bt.fj());
    }
    
    long eU() {
        return this.Bs.eU();
    }
    
    boolean eV() {
        return this.Bs.eV();
    }
    
    public void enableAdvertisingIdCollection(final boolean b) {
        if (!b) {
            this.qM.put("&ate", null);
            this.qM.put("&adid", null);
        }
        else {
            if (this.qM.containsKey("&ate")) {
                this.qM.remove("&ate");
            }
            if (this.qM.containsKey("&adid")) {
                this.qM.remove("&adid");
            }
        }
    }
    
    public void enableAutoActivityTracking(final boolean b) {
        this.Bs.enableAutoActivityTracking(b);
    }
    
    public void enableExceptionReporting(final boolean br) {
        if (this.Br == br) {
            return;
        }
        this.Br = br;
        if (br) {
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)(this.Bu = new ExceptionReporter(this, Thread.getDefaultUncaughtExceptionHandler(), this.mContext)));
            z.V("Uncaught exceptions will be reported to Google Analytics.");
            return;
        }
        if (this.Bu != null) {
            Thread.setDefaultUncaughtExceptionHandler(this.Bu.dZ());
        }
        else {
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
        z.V("Uncaught exceptions will not be reported to Google Analytics.");
    }
    
    public String get(final String s) {
        t.eq().a(t$a.zo);
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (this.qM.containsKey(s)) {
                return this.qM.get(s);
            }
            if (s.equals("&ul")) {
                return aj.a(Locale.getDefault());
            }
            if (this.Bo != null && this.Bo.ac(s)) {
                return this.Bo.getValue(s);
            }
            if (this.Bp != null && this.Bp.ac(s)) {
                return this.Bp.getValue(s);
            }
            if (this.Bq != null && this.Bq.ac(s)) {
                return this.Bq.getValue(s);
            }
        }
        return null;
    }
    
    public void send(final Map<String, String> map) {
        t.eq().a(t$a.zq);
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.putAll(this.qM);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (TextUtils.isEmpty((CharSequence)hashMap.get("&tid"))) {
            z.W(String.format("Missing tracking id (%s) parameter.", "&tid"));
        }
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = hashMap.get("&t")))) {
            z.W(String.format("Missing hit type (%s) parameter.", "&t"));
            s = "";
        }
        if (this.Bs.eW()) {
            hashMap.put("&sc", "start");
        }
        final String lowerCase = s.toLowerCase();
        if ("screenview".equals(lowerCase) || "pageview".equals(lowerCase) || "appview".equals(lowerCase) || TextUtils.isEmpty((CharSequence)lowerCase)) {
            int n;
            if ((n = Integer.parseInt(this.qM.get("&a")) + 1) >= Integer.MAX_VALUE) {
                n = 1;
            }
            this.qM.put("&a", Integer.toString(n));
        }
        if (!lowerCase.equals("transaction") && !lowerCase.equals("item") && !this.Bn.eK()) {
            z.W("Too many hits sent too quickly, rate limiting invoked.");
            return;
        }
        this.Bm.u((Map<String, String>)hashMap);
    }
    
    public void set(final String s, final String s2) {
        n.b(s, (Object)"Key should be non-null");
        t.eq().a(t$a.zp);
        this.qM.put(s, s2);
    }
    
    public void setAnonymizeIp(final boolean b) {
        this.set("&aip", aj.C(b));
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
            z.W("Invalid width or height. The values should be non-negative.");
            return;
        }
        this.set("&sr", n + "x" + n2);
    }
    
    public void setSessionTimeout(final long n) {
        this.Bs.setSessionTimeout(1000L * n);
    }
    
    public void setTitle(final String s) {
        this.set("&dt", s);
    }
    
    public void setUseSecure(final boolean b) {
        this.set("useSecure", aj.C(b));
    }
    
    public void setViewportSize(final String s) {
        this.set("&vp", s);
    }
}
