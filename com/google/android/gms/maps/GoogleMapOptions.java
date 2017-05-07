// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import android.content.res.TypedArray;
import com.google.android.gms.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GoogleMapOptions implements SafeParcelable
{
    public static final a CREATOR;
    private final int BR;
    private Boolean aiA;
    private Boolean aiB;
    private Boolean aiC;
    private Boolean aiD;
    private Boolean aiE;
    private Boolean aiv;
    private Boolean aiw;
    private int aix;
    private CameraPosition aiy;
    private Boolean aiz;
    
    static {
        CREATOR = new a();
    }
    
    public GoogleMapOptions() {
        this.aix = -1;
        this.BR = 1;
    }
    
    GoogleMapOptions(final int br, final byte b, final byte b2, final int aix, final CameraPosition aiy, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8) {
        this.aix = -1;
        this.BR = br;
        this.aiv = com.google.android.gms.maps.internal.a.a(b);
        this.aiw = com.google.android.gms.maps.internal.a.a(b2);
        this.aix = aix;
        this.aiy = aiy;
        this.aiz = com.google.android.gms.maps.internal.a.a(b3);
        this.aiA = com.google.android.gms.maps.internal.a.a(b4);
        this.aiB = com.google.android.gms.maps.internal.a.a(b5);
        this.aiC = com.google.android.gms.maps.internal.a.a(b6);
        this.aiD = com.google.android.gms.maps.internal.a.a(b7);
        this.aiE = com.google.android.gms.maps.internal.a.a(b8);
    }
    
    public static GoogleMapOptions createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R$styleable.MapAttrs);
        final GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(R$styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(R$styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(R$styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(R$styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(R$styleable.MapAttrs_uiZoomControls, true));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, set));
        obtainAttributes.recycle();
        return googleMapOptions;
    }
    
    public GoogleMapOptions camera(final CameraPosition aiy) {
        this.aiy = aiy;
        return this;
    }
    
    public GoogleMapOptions compassEnabled(final boolean b) {
        this.aiA = b;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CameraPosition getCamera() {
        return this.aiy;
    }
    
    public Boolean getCompassEnabled() {
        return this.aiA;
    }
    
    public int getMapType() {
        return this.aix;
    }
    
    public Boolean getRotateGesturesEnabled() {
        return this.aiE;
    }
    
    public Boolean getScrollGesturesEnabled() {
        return this.aiB;
    }
    
    public Boolean getTiltGesturesEnabled() {
        return this.aiD;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.aiw;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public Boolean getZOrderOnTop() {
        return this.aiv;
    }
    
    public Boolean getZoomControlsEnabled() {
        return this.aiz;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.aiC;
    }
    
    public GoogleMapOptions mapType(final int aix) {
        this.aix = aix;
        return this;
    }
    
    byte mp() {
        return com.google.android.gms.maps.internal.a.c(this.aiv);
    }
    
    byte mq() {
        return com.google.android.gms.maps.internal.a.c(this.aiw);
    }
    
    byte mr() {
        return com.google.android.gms.maps.internal.a.c(this.aiz);
    }
    
    byte ms() {
        return com.google.android.gms.maps.internal.a.c(this.aiA);
    }
    
    byte mt() {
        return com.google.android.gms.maps.internal.a.c(this.aiB);
    }
    
    byte mu() {
        return com.google.android.gms.maps.internal.a.c(this.aiC);
    }
    
    byte mv() {
        return com.google.android.gms.maps.internal.a.c(this.aiD);
    }
    
    byte mw() {
        return com.google.android.gms.maps.internal.a.c(this.aiE);
    }
    
    public GoogleMapOptions rotateGesturesEnabled(final boolean b) {
        this.aiE = b;
        return this;
    }
    
    public GoogleMapOptions scrollGesturesEnabled(final boolean b) {
        this.aiB = b;
        return this;
    }
    
    public GoogleMapOptions tiltGesturesEnabled(final boolean b) {
        this.aiD = b;
        return this;
    }
    
    public GoogleMapOptions useViewLifecycleInFragment(final boolean b) {
        this.aiw = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            b.a(this, parcel, n);
            return;
        }
        a.a(this, parcel, n);
    }
    
    public GoogleMapOptions zOrderOnTop(final boolean b) {
        this.aiv = b;
        return this;
    }
    
    public GoogleMapOptions zoomControlsEnabled(final boolean b) {
        this.aiz = b;
        return this;
    }
    
    public GoogleMapOptions zoomGesturesEnabled(final boolean b) {
        this.aiC = b;
        return this;
    }
}
