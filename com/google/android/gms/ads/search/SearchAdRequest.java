// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.content.Context;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import com.google.android.gms.internal.af;

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
    private final af dW;
    private final int jj;
    private final int jk;
    private final int jl;
    private final int jm;
    private final int jn;
    private final int jo;
    private final int jp;
    private final int jq;
    private final String jr;
    private final int js;
    private final String jt;
    private final int ju;
    private final int jv;
    private final String jw;
    
    static {
        DEVICE_ID_EMULATOR = af.DEVICE_ID_EMULATOR;
    }
    
    private SearchAdRequest(final Builder builder) {
        this.jj = builder.jj;
        this.jk = builder.jk;
        this.jl = builder.jl;
        this.jm = builder.jm;
        this.jn = builder.jn;
        this.jo = builder.jo;
        this.jp = builder.jp;
        this.jq = builder.jq;
        this.jr = builder.jr;
        this.js = builder.js;
        this.jt = builder.jt;
        this.ju = builder.ju;
        this.jv = builder.jv;
        this.jw = builder.jw;
        this.dW = new af(builder.dX, this);
    }
    
    public int getAnchorTextColor() {
        return this.jj;
    }
    
    public int getBackgroundColor() {
        return this.jk;
    }
    
    public int getBackgroundGradientBottom() {
        return this.jl;
    }
    
    public int getBackgroundGradientTop() {
        return this.jm;
    }
    
    public int getBorderColor() {
        return this.jn;
    }
    
    public int getBorderThickness() {
        return this.jo;
    }
    
    public int getBorderType() {
        return this.jp;
    }
    
    public int getCallButtonColor() {
        return this.jq;
    }
    
    public String getCustomChannels() {
        return this.jr;
    }
    
    public int getDescriptionTextColor() {
        return this.js;
    }
    
    public String getFontFace() {
        return this.jt;
    }
    
    public int getHeaderTextColor() {
        return this.ju;
    }
    
    public int getHeaderTextSize() {
        return this.jv;
    }
    
    public Location getLocation() {
        return this.dW.getLocation();
    }
    
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.dW.getNetworkExtras(clazz);
    }
    
    public String getQuery() {
        return this.jw;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.dW.isTestDevice(context);
    }
    
    af v() {
        return this.dW;
    }
    
    public static final class Builder
    {
        private final af.a dX;
        private int jj;
        private int jk;
        private int jl;
        private int jm;
        private int jn;
        private int jo;
        private int jp;
        private int jq;
        private String jr;
        private int js;
        private String jt;
        private int ju;
        private int jv;
        private String jw;
        
        public Builder() {
            this.dX = new af.a();
            this.jp = 0;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.dX.a(networkExtras);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.dX.h(s);
            return this;
        }
        
        public SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }
        
        public Builder setAnchorTextColor(final int jj) {
            this.jj = jj;
            return this;
        }
        
        public Builder setBackgroundColor(final int jk) {
            this.jk = jk;
            this.jl = Color.argb(0, 0, 0, 0);
            this.jm = Color.argb(0, 0, 0, 0);
            return this;
        }
        
        public Builder setBackgroundGradient(final int jm, final int jl) {
            this.jk = Color.argb(0, 0, 0, 0);
            this.jl = jl;
            this.jm = jm;
            return this;
        }
        
        public Builder setBorderColor(final int jn) {
            this.jn = jn;
            return this;
        }
        
        public Builder setBorderThickness(final int jo) {
            this.jo = jo;
            return this;
        }
        
        public Builder setBorderType(final int jp) {
            this.jp = jp;
            return this;
        }
        
        public Builder setCallButtonColor(final int jq) {
            this.jq = jq;
            return this;
        }
        
        public Builder setCustomChannels(final String jr) {
            this.jr = jr;
            return this;
        }
        
        public Builder setDescriptionTextColor(final int js) {
            this.js = js;
            return this;
        }
        
        public Builder setFontFace(final String jt) {
            this.jt = jt;
            return this;
        }
        
        public Builder setHeaderTextColor(final int ju) {
            this.ju = ju;
            return this;
        }
        
        public Builder setHeaderTextSize(final int jv) {
            this.jv = jv;
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.dX.a(location);
            return this;
        }
        
        public Builder setQuery(final String jw) {
            this.jw = jw;
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.dX.e(b);
            return this;
        }
    }
}
