// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import java.util.Collections;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import java.util.Map;
import android.location.Location;
import java.util.Set;
import java.util.Date;

public final class as
{
    public static final String DEVICE_ID_EMULATOR;
    private final Date d;
    private final Set<String> f;
    private final Location h;
    private final String lY;
    private final int lZ;
    private final boolean ma;
    private final Map<Class<? extends MediationAdapter>, Bundle> mb;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> mc;
    private final String md;
    private final SearchAdRequest me;
    private final int mf;
    private final Set<String> mg;
    
    static {
        DEVICE_ID_EMULATOR = dv.u("emulator");
    }
    
    public as(final a a) {
        this(a, null);
    }
    
    public as(final a a, final SearchAdRequest me) {
        this.d = a.d;
        this.lY = a.lY;
        this.lZ = a.lZ;
        this.f = Collections.unmodifiableSet((Set<? extends String>)a.mh);
        this.h = a.h;
        this.ma = a.ma;
        this.mb = Collections.unmodifiableMap((Map<? extends Class<? extends MediationAdapter>, ? extends Bundle>)a.mi);
        this.mc = Collections.unmodifiableMap((Map<? extends Class<? extends NetworkExtras>, ? extends NetworkExtras>)a.mj);
        this.md = a.md;
        this.me = me;
        this.mf = a.mf;
        this.mg = Collections.unmodifiableSet((Set<? extends String>)a.mk);
    }
    
    public SearchAdRequest aB() {
        return this.me;
    }
    
    public Map<Class<? extends NetworkExtras>, NetworkExtras> aC() {
        return this.mc;
    }
    
    public Map<Class<? extends MediationAdapter>, Bundle> aD() {
        return this.mb;
    }
    
    public int aE() {
        return this.mf;
    }
    
    public Date getBirthday() {
        return this.d;
    }
    
    public String getContentUrl() {
        return this.lY;
    }
    
    public int getGender() {
        return this.lZ;
    }
    
    public Set<String> getKeywords() {
        return this.f;
    }
    
    public Location getLocation() {
        return this.h;
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.ma;
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return (T)this.mc.get(clazz);
    }
    
    public Bundle getNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz) {
        return this.mb.get(clazz);
    }
    
    public String getPublisherProvidedId() {
        return this.md;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.mg.contains(dv.l(context));
    }
    
    public static final class a
    {
        private Date d;
        private Location h;
        private String lY;
        private int lZ;
        private boolean ma;
        private String md;
        private int mf;
        private final HashSet<String> mh;
        private final HashMap<Class<? extends MediationAdapter>, Bundle> mi;
        private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> mj;
        private final HashSet<String> mk;
        
        public a() {
            this.mh = new HashSet<String>();
            this.mi = new HashMap<Class<? extends MediationAdapter>, Bundle>();
            this.mj = new HashMap<Class<? extends NetworkExtras>, NetworkExtras>();
            this.mk = new HashSet<String>();
            this.lZ = -1;
            this.ma = false;
            this.mf = -1;
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
            this.mj.put(networkExtras.getClass(), networkExtras);
        }
        
        public void a(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.mi.put(clazz, bundle);
        }
        
        public void a(final Date d) {
            this.d = d;
        }
        
        public void d(final int lz) {
            this.lZ = lz;
        }
        
        public void f(final boolean ma) {
            this.ma = ma;
        }
        
        public void g(final String s) {
            this.mh.add(s);
        }
        
        public void g(final boolean b) {
            int mf;
            if (b) {
                mf = 1;
            }
            else {
                mf = 0;
            }
            this.mf = mf;
        }
        
        public void h(final String s) {
            this.mk.add(s);
        }
        
        public void i(final String ly) {
            this.lY = ly;
        }
        
        public void j(final String md) {
            this.md = md;
        }
    }
}
