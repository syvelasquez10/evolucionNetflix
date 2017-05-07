// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.graphics.Color;
import com.google.android.gms.internal.bg$a;
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
    
    private SearchAdRequest(final SearchAdRequest$Builder searchAdRequest$Builder) {
        this.xl = searchAdRequest$Builder.xl;
        this.xm = searchAdRequest$Builder.xm;
        this.xn = searchAdRequest$Builder.xn;
        this.xo = searchAdRequest$Builder.xo;
        this.xp = searchAdRequest$Builder.xp;
        this.xq = searchAdRequest$Builder.xq;
        this.xr = searchAdRequest$Builder.xr;
        this.xs = searchAdRequest$Builder.xs;
        this.xt = searchAdRequest$Builder.xt;
        this.xu = searchAdRequest$Builder.xu;
        this.xv = searchAdRequest$Builder.xv;
        this.xw = searchAdRequest$Builder.xw;
        this.xx = searchAdRequest$Builder.xx;
        this.xy = searchAdRequest$Builder.xy;
        this.ld = new bg(searchAdRequest$Builder.le, this);
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
}
