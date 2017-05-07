// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.n;
import java.util.Locale;
import android.text.TextUtils;
import java.util.Random;
import android.content.Context;
import java.util.Map;
import java.util.HashMap;
import android.app.Activity;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;

class Tracker$a implements GoogleAnalytics$a
{
    final /* synthetic */ Tracker BA;
    private boolean Bv;
    private int Bw;
    private long Bx;
    private boolean By;
    private long Bz;
    private ju yD;
    
    public Tracker$a(final Tracker ba) {
        this.BA = ba;
        this.Bv = false;
        this.Bw = 0;
        this.Bx = -1L;
        this.By = false;
        this.yD = jw.hA();
    }
    
    private void eX() {
        final GoogleAnalytics ee = GoogleAnalytics.eE();
        if (ee == null) {
            z.T("GoogleAnalytics isn't initialized for the Tracker!");
            return;
        }
        if (this.Bx >= 0L || this.Bv) {
            ee.a(this.BA.Bs);
            return;
        }
        ee.b(this.BA.Bs);
    }
    
    public long eU() {
        return this.Bx;
    }
    
    public boolean eV() {
        return this.Bv;
    }
    
    public boolean eW() {
        final boolean by = this.By;
        this.By = false;
        return by;
    }
    
    boolean eY() {
        return this.yD.elapsedRealtime() >= this.Bz + Math.max(1000L, this.Bx);
    }
    
    public void enableAutoActivityTracking(final boolean bv) {
        this.Bv = bv;
        this.eX();
    }
    
    @Override
    public void i(final Activity activity) {
        t.eq().a(t$a.An);
        if (this.Bw == 0 && this.eY()) {
            this.By = true;
        }
        ++this.Bw;
        if (this.Bv) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("&t", "screenview");
            t.eq().B(true);
            final Tracker ba = this.BA;
            String s;
            if (this.BA.Bt != null) {
                s = this.BA.Bt.k(activity);
            }
            else {
                s = activity.getClass().getCanonicalName();
            }
            ba.set("&cd", s);
            this.BA.send(hashMap);
            t.eq().B(false);
        }
    }
    
    @Override
    public void j(final Activity activity) {
        t.eq().a(t$a.Ao);
        --this.Bw;
        this.Bw = Math.max(0, this.Bw);
        if (this.Bw == 0) {
            this.Bz = this.yD.elapsedRealtime();
        }
    }
    
    public void setSessionTimeout(final long bx) {
        this.Bx = bx;
        this.eX();
    }
}
