// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.r;
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
    private Boolean BJ;
    private Boolean BK;
    private int BL;
    private CameraPosition BM;
    private Boolean BN;
    private Boolean BO;
    private Boolean BP;
    private Boolean BQ;
    private Boolean BR;
    private Boolean BS;
    private final int kg;
    
    static {
        CREATOR = new GoogleMapOptionsCreator();
    }
    
    public GoogleMapOptions() {
        this.BL = -1;
        this.kg = 1;
    }
    
    GoogleMapOptions(final int kg, final byte b, final byte b2, final int bl, final CameraPosition bm, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8) {
        this.BL = -1;
        this.kg = kg;
        this.BJ = a.a(b);
        this.BK = a.a(b2);
        this.BL = bl;
        this.BM = bm;
        this.BN = a.a(b3);
        this.BO = a.a(b4);
        this.BP = a.a(b5);
        this.BQ = a.a(b6);
        this.BR = a.a(b7);
        this.BS = a.a(b8);
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
    
    public GoogleMapOptions camera(final CameraPosition bm) {
        this.BM = bm;
        return this;
    }
    
    public GoogleMapOptions compassEnabled(final boolean b) {
        this.BO = b;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    byte eo() {
        return a.c(this.BJ);
    }
    
    byte ep() {
        return a.c(this.BK);
    }
    
    byte eq() {
        return a.c(this.BN);
    }
    
    byte er() {
        return a.c(this.BO);
    }
    
    byte es() {
        return a.c(this.BP);
    }
    
    byte et() {
        return a.c(this.BQ);
    }
    
    byte eu() {
        return a.c(this.BR);
    }
    
    byte ev() {
        return a.c(this.BS);
    }
    
    public CameraPosition getCamera() {
        return this.BM;
    }
    
    public Boolean getCompassEnabled() {
        return this.BO;
    }
    
    public int getMapType() {
        return this.BL;
    }
    
    public Boolean getRotateGesturesEnabled() {
        return this.BS;
    }
    
    public Boolean getScrollGesturesEnabled() {
        return this.BP;
    }
    
    public Boolean getTiltGesturesEnabled() {
        return this.BR;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.BK;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public Boolean getZOrderOnTop() {
        return this.BJ;
    }
    
    public Boolean getZoomControlsEnabled() {
        return this.BN;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.BQ;
    }
    
    public GoogleMapOptions mapType(final int bl) {
        this.BL = bl;
        return this;
    }
    
    public GoogleMapOptions rotateGesturesEnabled(final boolean b) {
        this.BS = b;
        return this;
    }
    
    public GoogleMapOptions scrollGesturesEnabled(final boolean b) {
        this.BP = b;
        return this;
    }
    
    public GoogleMapOptions tiltGesturesEnabled(final boolean b) {
        this.BR = b;
        return this;
    }
    
    public GoogleMapOptions useViewLifecycleInFragment(final boolean b) {
        this.BK = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            com.google.android.gms.maps.a.a(this, parcel, n);
            return;
        }
        GoogleMapOptionsCreator.a(this, parcel, n);
    }
    
    public GoogleMapOptions zOrderOnTop(final boolean b) {
        this.BJ = b;
        return this;
    }
    
    public GoogleMapOptions zoomControlsEnabled(final boolean b) {
        this.BN = b;
        return this;
    }
    
    public GoogleMapOptions zoomGesturesEnabled(final boolean b) {
        this.BQ = b;
        return this;
    }
}
