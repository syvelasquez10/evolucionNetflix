// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.dynamic.d$a;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MarkerOptions implements SafeParcelable
{
    public static final k CREATOR;
    private final int BR;
    private String No;
    private boolean ajB;
    private float ajJ;
    private float ajK;
    private String ajS;
    private BitmapDescriptor ajT;
    private boolean ajU;
    private boolean ajV;
    private float ajW;
    private float ajX;
    private float ajY;
    private LatLng aja;
    private float mAlpha;
    
    static {
        CREATOR = new k();
    }
    
    public MarkerOptions() {
        this.ajJ = 0.5f;
        this.ajK = 1.0f;
        this.ajB = true;
        this.ajV = false;
        this.ajW = 0.0f;
        this.ajX = 0.5f;
        this.ajY = 0.0f;
        this.mAlpha = 1.0f;
        this.BR = 1;
    }
    
    MarkerOptions(final int br, final LatLng aja, final String no, final String ajS, final IBinder binder, final float ajJ, final float ajK, final boolean ajU, final boolean ajB, final boolean ajV, final float ajW, final float ajX, final float ajY, final float mAlpha) {
        this.ajJ = 0.5f;
        this.ajK = 1.0f;
        this.ajB = true;
        this.ajV = false;
        this.ajW = 0.0f;
        this.ajX = 0.5f;
        this.ajY = 0.0f;
        this.mAlpha = 1.0f;
        this.BR = br;
        this.aja = aja;
        this.No = no;
        this.ajS = ajS;
        BitmapDescriptor ajT;
        if (binder == null) {
            ajT = null;
        }
        else {
            ajT = new BitmapDescriptor(d$a.am(binder));
        }
        this.ajT = ajT;
        this.ajJ = ajJ;
        this.ajK = ajK;
        this.ajU = ajU;
        this.ajB = ajB;
        this.ajV = ajV;
        this.ajW = ajW;
        this.ajX = ajX;
        this.ajY = ajY;
        this.mAlpha = mAlpha;
    }
    
    public MarkerOptions alpha(final float mAlpha) {
        this.mAlpha = mAlpha;
        return this;
    }
    
    public MarkerOptions anchor(final float ajJ, final float ajK) {
        this.ajJ = ajJ;
        this.ajK = ajK;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MarkerOptions draggable(final boolean ajU) {
        this.ajU = ajU;
        return this;
    }
    
    public MarkerOptions flat(final boolean ajV) {
        this.ajV = ajV;
        return this;
    }
    
    public float getAlpha() {
        return this.mAlpha;
    }
    
    public float getAnchorU() {
        return this.ajJ;
    }
    
    public float getAnchorV() {
        return this.ajK;
    }
    
    public BitmapDescriptor getIcon() {
        return this.ajT;
    }
    
    public float getInfoWindowAnchorU() {
        return this.ajX;
    }
    
    public float getInfoWindowAnchorV() {
        return this.ajY;
    }
    
    public LatLng getPosition() {
        return this.aja;
    }
    
    public float getRotation() {
        return this.ajW;
    }
    
    public String getSnippet() {
        return this.ajS;
    }
    
    public String getTitle() {
        return this.No;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public MarkerOptions icon(final BitmapDescriptor ajT) {
        this.ajT = ajT;
        return this;
    }
    
    public MarkerOptions infoWindowAnchor(final float ajX, final float ajY) {
        this.ajX = ajX;
        this.ajY = ajY;
        return this;
    }
    
    public boolean isDraggable() {
        return this.ajU;
    }
    
    public boolean isFlat() {
        return this.ajV;
    }
    
    public boolean isVisible() {
        return this.ajB;
    }
    
    IBinder mN() {
        if (this.ajT == null) {
            return null;
        }
        return this.ajT.mm().asBinder();
    }
    
    public MarkerOptions position(final LatLng aja) {
        this.aja = aja;
        return this;
    }
    
    public MarkerOptions rotation(final float ajW) {
        this.ajW = ajW;
        return this;
    }
    
    public MarkerOptions snippet(final String ajS) {
        this.ajS = ajS;
        return this;
    }
    
    public MarkerOptions title(final String no) {
        this.No = no;
        return this;
    }
    
    public MarkerOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            l.a(this, parcel, n);
            return;
        }
        k.a(this, parcel, n);
    }
}
