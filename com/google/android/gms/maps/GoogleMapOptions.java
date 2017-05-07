// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import android.content.res.TypedArray;
import com.google.android.gms.R;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GoogleMapOptions implements SafeParcelable
{
    public static final GoogleMapOptionsCreator CREATOR;
    private Boolean RI;
    private Boolean RJ;
    private int RK;
    private CameraPosition RL;
    private Boolean RM;
    private Boolean RN;
    private Boolean RO;
    private Boolean RP;
    private Boolean RQ;
    private Boolean RR;
    private final int xH;
    
    static {
        CREATOR = new GoogleMapOptionsCreator();
    }
    
    public GoogleMapOptions() {
        this.RK = -1;
        this.xH = 1;
    }
    
    GoogleMapOptions(final int xh, final byte b, final byte b2, final int rk, final CameraPosition rl, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8) {
        this.RK = -1;
        this.xH = xh;
        this.RI = a.a(b);
        this.RJ = a.a(b2);
        this.RK = rk;
        this.RL = rl;
        this.RM = a.a(b3);
        this.RN = a.a(b4);
        this.RO = a.a(b5);
        this.RP = a.a(b6);
        this.RQ = a.a(b7);
        this.RR = a.a(b8);
    }
    
    public static GoogleMapOptions createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.MapAttrs);
        final GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(0)) {
            googleMapOptions.mapType(obtainAttributes.getInt(0, -1));
        }
        if (obtainAttributes.hasValue(13)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(13, false));
        }
        if (obtainAttributes.hasValue(12)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(12, false));
        }
        if (obtainAttributes.hasValue(6)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(6, true));
        }
        if (obtainAttributes.hasValue(7)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(7, true));
        }
        if (obtainAttributes.hasValue(8)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(8, true));
        }
        if (obtainAttributes.hasValue(9)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(9, true));
        }
        if (obtainAttributes.hasValue(11)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(11, true));
        }
        if (obtainAttributes.hasValue(10)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(10, true));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, set));
        obtainAttributes.recycle();
        return googleMapOptions;
    }
    
    public GoogleMapOptions camera(final CameraPosition rl) {
        this.RL = rl;
        return this;
    }
    
    public GoogleMapOptions compassEnabled(final boolean b) {
        this.RN = b;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CameraPosition getCamera() {
        return this.RL;
    }
    
    public Boolean getCompassEnabled() {
        return this.RN;
    }
    
    public int getMapType() {
        return this.RK;
    }
    
    public Boolean getRotateGesturesEnabled() {
        return this.RR;
    }
    
    public Boolean getScrollGesturesEnabled() {
        return this.RO;
    }
    
    public Boolean getTiltGesturesEnabled() {
        return this.RQ;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.RJ;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public Boolean getZOrderOnTop() {
        return this.RI;
    }
    
    public Boolean getZoomControlsEnabled() {
        return this.RM;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.RP;
    }
    
    byte ig() {
        return a.c(this.RI);
    }
    
    byte ih() {
        return a.c(this.RJ);
    }
    
    byte ii() {
        return a.c(this.RM);
    }
    
    byte ij() {
        return a.c(this.RN);
    }
    
    byte ik() {
        return a.c(this.RO);
    }
    
    byte il() {
        return a.c(this.RP);
    }
    
    byte im() {
        return a.c(this.RQ);
    }
    
    byte in() {
        return a.c(this.RR);
    }
    
    public GoogleMapOptions mapType(final int rk) {
        this.RK = rk;
        return this;
    }
    
    public GoogleMapOptions rotateGesturesEnabled(final boolean b) {
        this.RR = b;
        return this;
    }
    
    public GoogleMapOptions scrollGesturesEnabled(final boolean b) {
        this.RO = b;
        return this;
    }
    
    public GoogleMapOptions tiltGesturesEnabled(final boolean b) {
        this.RQ = b;
        return this;
    }
    
    public GoogleMapOptions useViewLifecycleInFragment(final boolean b) {
        this.RJ = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            com.google.android.gms.maps.a.a(this, parcel, n);
            return;
        }
        GoogleMapOptionsCreator.a(this, parcel, n);
    }
    
    public GoogleMapOptions zOrderOnTop(final boolean b) {
        this.RI = b;
        return this;
    }
    
    public GoogleMapOptions zoomControlsEnabled(final boolean b) {
        this.RM = b;
        return this;
    }
    
    public GoogleMapOptions zoomGesturesEnabled(final boolean b) {
        this.RP = b;
        return this;
    }
}
