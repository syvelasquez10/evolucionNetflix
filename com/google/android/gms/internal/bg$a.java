// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.HashMap;
import java.util.HashSet;
import android.os.Bundle;
import android.location.Location;
import java.util.Date;

public final class bg$a
{
    private Date d;
    private Location h;
    private String ol;
    private int om;
    private boolean on;
    private final Bundle oo;
    private String oq;
    private int os;
    private final HashSet<String> ou;
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> ov;
    private final HashSet<String> ow;
    
    public bg$a() {
        this.ou = new HashSet<String>();
        this.oo = new Bundle();
        this.ov = new HashMap<Class<? extends NetworkExtras>, NetworkExtras>();
        this.ow = new HashSet<String>();
        this.om = -1;
        this.on = false;
        this.os = -1;
    }
    
    public void a(final Location h) {
        this.h = h;
    }
    
    @Deprecated
    public void a(final NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            this.a(AdMobAdapter.class, ((AdMobExtras)networkExtras).getExtras());
            return;
        }
        this.ov.put(networkExtras.getClass(), networkExtras);
    }
    
    public void a(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
        this.oo.putBundle(clazz.getName(), bundle);
    }
    
    public void a(final Date d) {
        this.d = d;
    }
    
    public void b(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
        if (this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.oo.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(clazz.getName(), bundle);
    }
    
    public void g(final int om) {
        this.om = om;
    }
    
    public void g(final boolean on) {
        this.on = on;
    }
    
    public void h(final boolean b) {
        int os;
        if (b) {
            os = 1;
        }
        else {
            os = 0;
        }
        this.os = os;
    }
    
    public void r(final String s) {
        this.ou.add(s);
    }
    
    public void s(final String s) {
        this.ow.add(s);
    }
    
    public void t(final String ol) {
        this.ol = ol;
    }
    
    public void u(final String oq) {
        this.oq = oq;
    }
}
