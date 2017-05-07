// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import com.google.android.gms.internal.as;

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
    private final as kp;
    private final int rR;
    private final int rS;
    private final int rT;
    private final int rU;
    private final int rV;
    private final int rW;
    private final int rX;
    private final int rY;
    private final String rZ;
    private final int sa;
    private final String sb;
    private final int sc;
    private final int sd;
    private final String se;
    
    static {
        DEVICE_ID_EMULATOR = as.DEVICE_ID_EMULATOR;
    }
    
    private SearchAdRequest(final Builder builder) {
        this.rR = builder.rR;
        this.rS = builder.rS;
        this.rT = builder.rT;
        this.rU = builder.rU;
        this.rV = builder.rV;
        this.rW = builder.rW;
        this.rX = builder.rX;
        this.rY = builder.rY;
        this.rZ = builder.rZ;
        this.sa = builder.sa;
        this.sb = builder.sb;
        this.sc = builder.sc;
        this.sd = builder.sd;
        this.se = builder.se;
        this.kp = new as(builder.kq, this);
    }
    
    as O() {
        return this.kp;
    }
    
    public int getAnchorTextColor() {
        return this.rR;
    }
    
    public int getBackgroundColor() {
        return this.rS;
    }
    
    public int getBackgroundGradientBottom() {
        return this.rT;
    }
    
    public int getBackgroundGradientTop() {
        return this.rU;
    }
    
    public int getBorderColor() {
        return this.rV;
    }
    
    public int getBorderThickness() {
        return this.rW;
    }
    
    public int getBorderType() {
        return this.rX;
    }
    
    public int getCallButtonColor() {
        return this.rY;
    }
    
    public String getCustomChannels() {
        return this.rZ;
    }
    
    public int getDescriptionTextColor() {
        return this.sa;
    }
    
    public String getFontFace() {
        return this.sb;
    }
    
    public int getHeaderTextColor() {
        return this.sc;
    }
    
    public int getHeaderTextSize() {
        return this.sd;
    }
    
    public Location getLocation() {
        return this.kp.getLocation();
    }
    
    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.kp.getNetworkExtras(clazz);
    }
    
    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.kp.getNetworkExtrasBundle(clazz);
    }
    
    public String getQuery() {
        return this.se;
    }
    
    public boolean isTestDevice(final Context context) {
        return this.kp.isTestDevice(context);
    }
    
    public static final class Builder
    {
        private final as.a kq;
        private int rR;
        private int rS;
        private int rT;
        private int rU;
        private int rV;
        private int rW;
        private int rX;
        private int rY;
        private String rZ;
        private int sa;
        private String sb;
        private int sc;
        private int sd;
        private String se;
        
        public Builder() {
            this.kq = new as.a();
            this.rX = 0;
        }
        
        public Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.kq.a(networkExtras);
            return this;
        }
        
        public Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.kq.a(clazz, bundle);
            return this;
        }
        
        public Builder addTestDevice(final String s) {
            this.kq.h(s);
            return this;
        }
        
        public SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }
        
        public Builder setAnchorTextColor(final int rr) {
            this.rR = rr;
            return this;
        }
        
        public Builder setBackgroundColor(final int rs) {
            this.rS = rs;
            this.rT = Color.argb(0, 0, 0, 0);
            this.rU = Color.argb(0, 0, 0, 0);
            return this;
        }
        
        public Builder setBackgroundGradient(final int ru, final int rt) {
            this.rS = Color.argb(0, 0, 0, 0);
            this.rT = rt;
            this.rU = ru;
            return this;
        }
        
        public Builder setBorderColor(final int rv) {
            this.rV = rv;
            return this;
        }
        
        public Builder setBorderThickness(final int rw) {
            this.rW = rw;
            return this;
        }
        
        public Builder setBorderType(final int rx) {
            this.rX = rx;
            return this;
        }
        
        public Builder setCallButtonColor(final int ry) {
            this.rY = ry;
            return this;
        }
        
        public Builder setCustomChannels(final String rz) {
            this.rZ = rz;
            return this;
        }
        
        public Builder setDescriptionTextColor(final int sa) {
            this.sa = sa;
            return this;
        }
        
        public Builder setFontFace(final String sb) {
            this.sb = sb;
            return this;
        }
        
        public Builder setHeaderTextColor(final int sc) {
            this.sc = sc;
            return this;
        }
        
        public Builder setHeaderTextSize(final int sd) {
            this.sd = sd;
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.kq.a(location);
            return this;
        }
        
        public Builder setQuery(final String se) {
            this.se = se;
            return this;
        }
        
        public Builder tagForChildDirectedTreatment(final boolean b) {
            this.kq.g(b);
            return this;
        }
    }
}
