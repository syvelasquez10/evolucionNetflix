// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import java.util.HashMap;
import java.util.HashSet;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Collections;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;
import android.os.Bundle;
import android.location.Location;
import java.util.Set;
import java.util.Date;

@ez
public final class bg
{
    public static final String DEVICE_ID_EMULATOR;
    private final Date d;
    private final Set<String> f;
    private final Location h;
    private final String ol;
    private final int om;
    private final boolean on;
    private final Bundle oo;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> op;
    private final String oq;
    private final SearchAdRequest or;
    private final int os;
    private final Set<String> ot;
    
    static {
        DEVICE_ID_EMULATOR = gr.R("emulator");
    }
    
    public bg(final a a) {
        this(a, null);
    }
    
    public bg(final a a, final SearchAdRequest or) {
        this.d = a.d;
        this.ol = a.ol;
        this.om = a.om;
        this.f = Collections.unmodifiableSet((Set<? extends String>)a.ou);
        this.h = a.h;
        this.on = a.on;
        this.oo = a.oo;
        this.op = Collections.unmodifiableMap((Map<? extends Class<? extends NetworkExtras>, ? extends NetworkExtras>)a.ov);
        this.oq = a.oq;
        this.or = or;
        this.os = a.os;
        this.ot = Collections.unmodifiableSet((Set<? extends String>)a.ow);
    }
    
    public SearchAdRequest bd() {
        return this.or;
    }
    
    public Map<Class<? extends NetworkExtras>, NetworkExtras> be() {
        return this.op;
    }
    
    public Bundle bf() {
        return this.oo;
    }
    
    public int bg() {
        return this.os;
    }
    
    public Date getBirthday() {
        return this.d;
    }
    
    public String getContentUrl() {
        return this.ol;
    }
    
    public Bundle getCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz) {
        final Bundle bundle = this.oo.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(clazz.getClass().getName());
        }
        return null;
    }
    
    public int getGender() {
        return this.om;
    }
    
    public Set<String> getKeywords() {
        return this.f;
    }
    
    public Location getLocation() {
        return this.h;
    }
    
    public boolean getManualImpressionsEnabled() {
        return this.on;
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return (T)this.op.get(clazz);
    }
    
    public Bundle getNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz) {
        return this.oo.getBundle(clazz.getName());
    }
    
    public String getPublisherProvidedId() {
        return this.oq;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.ot.contains(gr.v(context));
    }
    
    public static final class a
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
        
        public a() {
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
}
