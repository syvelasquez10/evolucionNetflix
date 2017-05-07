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
    
    public bg(final bg$a bg$a) {
        this(bg$a, null);
    }
    
    public bg(final bg$a bg$a, final SearchAdRequest or) {
        this.d = bg$a.d;
        this.ol = bg$a.ol;
        this.om = bg$a.om;
        this.f = Collections.unmodifiableSet((Set<? extends String>)bg$a.ou);
        this.h = bg$a.h;
        this.on = bg$a.on;
        this.oo = bg$a.oo;
        this.op = Collections.unmodifiableMap((Map<? extends Class<? extends NetworkExtras>, ? extends NetworkExtras>)bg$a.ov);
        this.oq = bg$a.oq;
        this.or = or;
        this.os = bg$a.os;
        this.ot = Collections.unmodifiableSet((Set<? extends String>)bg$a.ow);
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
}
