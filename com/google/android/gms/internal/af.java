// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import java.util.Collections;
import java.util.Set;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;
import android.location.Location;
import java.util.Date;

public final class af
{
    public static final String DEVICE_ID_EMULATOR;
    private final Date d;
    private final int eL;
    private final Location eM;
    private final boolean eN;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> eO;
    private final String eP;
    private final SearchAdRequest eQ;
    private final int eR;
    private final Set<String> eS;
    private final Set<String> f;
    
    static {
        DEVICE_ID_EMULATOR = cs.q("emulator");
    }
    
    public af(final a a) {
        this(a, null);
    }
    
    public af(final a a, final SearchAdRequest eq) {
        this.d = a.d;
        this.eL = a.eL;
        this.f = Collections.unmodifiableSet((Set<? extends String>)a.eT);
        this.eM = a.eM;
        this.eN = a.eN;
        this.eO = Collections.unmodifiableMap((Map<? extends Class<? extends NetworkExtras>, ? extends NetworkExtras>)a.eU);
        this.eP = a.eP;
        this.eQ = eq;
        this.eR = a.eR;
        this.eS = Collections.unmodifiableSet((Set<? extends String>)a.eV);
    }
    
    public SearchAdRequest Q() {
        return this.eQ;
    }
    
    public Map<Class<? extends NetworkExtras>, NetworkExtras> R() {
        return this.eO;
    }
    
    public int S() {
        return this.eR;
    }
    
    public Date getBirthday() {
        return this.d;
    }
    
    public int getGender() {
        return this.eL;
    }
    
    public Set<String> getKeywords() {
        return this.f;
    }
    
    public Location getLocation() {
        return this.eM;
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.eN;
    }
    
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return (T)this.eO.get(clazz);
    }
    
    public String getPublisherProvidedId() {
        return this.eP;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.eS.contains(cs.l(context));
    }
    
    public static final class a
    {
        private Date d;
        private int eL;
        private Location eM;
        private boolean eN;
        private String eP;
        private int eR;
        private final HashSet<String> eT;
        private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> eU;
        private final HashSet<String> eV;
        
        public a() {
            this.eT = new HashSet<String>();
            this.eU = new HashMap<Class<? extends NetworkExtras>, NetworkExtras>();
            this.eV = new HashSet<String>();
            this.eL = -1;
            this.eN = false;
            this.eR = -1;
        }
        
        public void a(final Location em) {
            this.eM = em;
        }
        
        public void a(final NetworkExtras networkExtras) {
            this.eU.put(networkExtras.getClass(), networkExtras);
        }
        
        public void a(final Date d) {
            this.d = d;
        }
        
        public void d(final int el) {
            this.eL = el;
        }
        
        public void d(final boolean en) {
            this.eN = en;
        }
        
        public void e(final boolean b) {
            int er;
            if (b) {
                er = 1;
            }
            else {
                er = 0;
            }
            this.eR = er;
        }
        
        public void g(final String s) {
            this.eT.add(s);
        }
        
        public void h(final String s) {
            this.eV.add(s);
        }
        
        public void i(final String ep) {
            this.eP = ep;
        }
    }
}
