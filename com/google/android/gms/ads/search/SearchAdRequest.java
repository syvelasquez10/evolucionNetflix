// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.bg;

public final class SearchAdRequest
{
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final bg ld;
    private final int xl;
    private final int xm;
    private final int xn;
    private final int xo;
    private final int xp;
    private final int xq;
    private final int xr;
    private final int xs;
    private final String xt;
    private final int xu;
    private final String xv;
    private final int xw;
    private final int xx;
    private final String xy;
    
    static {
        DEVICE_ID_EMULATOR = bg.DEVICE_ID_EMULATOR;
    }
    
    private SearchAdRequest(final Builder builder) {
        this.xl = builder.xl;
        this.xm = builder.xm;
        this.xn = builder.xn;
        this.xo = builder.xo;
        this.xp = builder.xp;
        this.xq = builder.xq;
        this.xr = builder.xr;
        this.xs = builder.xs;
        this.xt = builder.xt;
        this.xu = builder.xu;
        this.xv = builder.xv;
        this.xw = builder.xw;
        this.xx = builder.xx;
        this.xy = builder.xy;
        this.ld = new bg(builder.le, this);
    }
    
    bg V() {
        return this.ld;
    }
    
    public int getAnchorTextColor() {
        return this.xl;
    }
    
    public int getBackgroundColor() {
        return this.xm;
    }
    
    public int getBackgroundGradientBottom() {
        return this.xn;
    }
    
    public int getBackgroundGradientTop() {
        return this.xo;
    }
    
    public int getBorderColor() {
        return this.xp;
    }
    
    public int getBorderThickness() {
        return this.xq;
    }
    
    public int getBorderType() {
        return this.xr;
    }
    
    public int getCallButtonColor() {
        return this.xs;
    }
    
    public String getCustomChannels() {
        return this.xt;
    }
    
    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.ld.getCustomEventExtrasBundle(clazz);
    }
    
    public int getDescriptionTextColor() {
        return this.xu;
    }
    
    public String getFontFace() {
        return this.xv;
    }
    
    public int getHeaderTextColor() {
        return this.xw;
    }
    
    public int getHeaderTextSize() {
        return this.xx;
    }
    
    public Location getLocation() {
        return this.ld.getLocation();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.ld.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.ld.getNetworkExtrasBundle(clazz);
    }
    
    public String getQuery() {
        return this.xy;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.ld.isTestDevice(context);
    }
    
    public static final class Builder
    {
        private final bg.a le;
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
        
        public Builder() {
            this.le = new bg.a();
            this.xr = 0;
        }
        
        public Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.le.b(clazz, bundle);
            return this;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.le.a(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.le.a(clazz, bundle);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.le.s(s);
            return this;
        }
        
        public SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }
        
        public Builder setAnchorTextColor(final int xl) {
            this.xl = xl;
            return this;
        }
        
        public Builder setBackgroundColor(final int xm) {
            this.xm = xm;
            this.xn = Color.argb(0, 0, 0, 0);
            this.xo = Color.argb(0, 0, 0, 0);
            return this;
        }
        
        public Builder setBackgroundGradient(final int xo, final int xn) {
            this.xm = Color.argb(0, 0, 0, 0);
            this.xn = xn;
            this.xo = xo;
            return this;
        }
        
        public Builder setBorderColor(final int xp) {
            this.xp = xp;
            return this;
        }
        
        public Builder setBorderThickness(final int xq) {
            this.xq = xq;
            return this;
        }
        
        public Builder setBorderType(final int xr) {
            this.xr = xr;
            return this;
        }
        
        public Builder setCallButtonColor(final int xs) {
            this.xs = xs;
            return this;
        }
        
        public Builder setCustomChannels(final String xt) {
            this.xt = xt;
            return this;
        }
        
        public Builder setDescriptionTextColor(final int xu) {
            this.xu = xu;
            return this;
        }
        
        public Builder setFontFace(final String xv) {
            this.xv = xv;
            return this;
        }
        
        public Builder setHeaderTextColor(final int xw) {
            this.xw = xw;
            return this;
        }
        
        public Builder setHeaderTextSize(final int xx) {
            this.xx = xx;
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.le.a(location);
            return this;
        }
        
        public Builder setQuery(final String xy) {
            this.xy = xy;
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.le.h(b);
            return this;
        }
    }
}
