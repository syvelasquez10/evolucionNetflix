// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.location.Location;
import android.graphics.Color;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.bg$a;

public final class SearchAdRequest$Builder
{
    private final bg$a le;
    private int xl;
    private int xm;
    private int xn;
    private int xo;
    private int xp;
    private int xq;
    private int xr;
    private int xs;
    private String xt;
    private int xu;
    private String xv;
    private int xw;
    private int xx;
    private String xy;
    
    public SearchAdRequest$Builder() {
        this.le = new bg$a();
        this.xr = 0;
    }
    
    public SearchAdRequest$Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
        this.le.b(clazz, bundle);
        return this;
    }
    
    public SearchAdRequest$Builder addNetworkExtras(final NetworkExtras networkExtras) {
        this.le.a(networkExtras);
        return this;
    }
    
    public SearchAdRequest$Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
        this.le.a(clazz, bundle);
        return this;
    }
    
    public SearchAdRequest$Builder addTestDevice(final String s) {
        this.le.s(s);
        return this;
    }
    
    public SearchAdRequest build() {
        return new SearchAdRequest(this, null);
    }
    
    public SearchAdRequest$Builder setAnchorTextColor(final int xl) {
        this.xl = xl;
        return this;
    }
    
    public SearchAdRequest$Builder setBackgroundColor(final int xm) {
        this.xm = xm;
        this.xn = Color.argb(0, 0, 0, 0);
        this.xo = Color.argb(0, 0, 0, 0);
        return this;
    }
    
    public SearchAdRequest$Builder setBackgroundGradient(final int xo, final int xn) {
        this.xm = Color.argb(0, 0, 0, 0);
        this.xn = xn;
        this.xo = xo;
        return this;
    }
    
    public SearchAdRequest$Builder setBorderColor(final int xp) {
        this.xp = xp;
        return this;
    }
    
    public SearchAdRequest$Builder setBorderThickness(final int xq) {
        this.xq = xq;
        return this;
    }
    
    public SearchAdRequest$Builder setBorderType(final int xr) {
        this.xr = xr;
        return this;
    }
    
    public SearchAdRequest$Builder setCallButtonColor(final int xs) {
        this.xs = xs;
        return this;
    }
    
    public SearchAdRequest$Builder setCustomChannels(final String xt) {
        this.xt = xt;
        return this;
    }
    
    public SearchAdRequest$Builder setDescriptionTextColor(final int xu) {
        this.xu = xu;
        return this;
    }
    
    public SearchAdRequest$Builder setFontFace(final String xv) {
        this.xv = xv;
        return this;
    }
    
    public SearchAdRequest$Builder setHeaderTextColor(final int xw) {
        this.xw = xw;
        return this;
    }
    
    public SearchAdRequest$Builder setHeaderTextSize(final int xx) {
        this.xx = xx;
        return this;
    }
    
    public SearchAdRequest$Builder setLocation(final Location location) {
        this.le.a(location);
        return this;
    }
    
    public SearchAdRequest$Builder setQuery(final String xy) {
        this.xy = xy;
        return this;
    }
    
    public SearchAdRequest$Builder tagForChildDirectedTreatment(final boolean b) {
        this.le.h(b);
        return this;
    }
}
