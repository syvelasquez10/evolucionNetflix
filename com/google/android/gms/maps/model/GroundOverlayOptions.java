// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.dynamic.d$a;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GroundOverlayOptions implements SafeParcelable
{
    public static final e CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    private final int BR;
    private float ajA;
    private boolean ajB;
    private BitmapDescriptor ajD;
    private LatLng ajE;
    private float ajF;
    private float ajG;
    private LatLngBounds ajH;
    private float ajI;
    private float ajJ;
    private float ajK;
    private float ajt;
    
    static {
        CREATOR = new e();
    }
    
    public GroundOverlayOptions() {
        this.ajB = true;
        this.ajI = 0.0f;
        this.ajJ = 0.5f;
        this.ajK = 0.5f;
        this.BR = 1;
    }
    
    GroundOverlayOptions(final int br, final IBinder binder, final LatLng ajE, final float ajF, final float ajG, final LatLngBounds ajH, final float ajt, final float ajA, final boolean ajB, final float ajI, final float ajJ, final float ajK) {
        this.ajB = true;
        this.ajI = 0.0f;
        this.ajJ = 0.5f;
        this.ajK = 0.5f;
        this.BR = br;
        this.ajD = new BitmapDescriptor(d$a.am(binder));
        this.ajE = ajE;
        this.ajF = ajF;
        this.ajG = ajG;
        this.ajH = ajH;
        this.ajt = ajt;
        this.ajA = ajA;
        this.ajB = ajB;
        this.ajI = ajI;
        this.ajJ = ajJ;
        this.ajK = ajK;
    }
    
    private GroundOverlayOptions a(final LatLng ajE, final float ajF, final float ajG) {
        this.ajE = ajE;
        this.ajF = ajF;
        this.ajG = ajG;
        return this;
    }
    
    public GroundOverlayOptions anchor(final float ajJ, final float ajK) {
        this.ajJ = ajJ;
        this.ajK = ajK;
        return this;
    }
    
    public GroundOverlayOptions bearing(final float n) {
        this.ajt = (n % 360.0f + 360.0f) % 360.0f;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public float getAnchorU() {
        return this.ajJ;
    }
    
    public float getAnchorV() {
        return this.ajK;
    }
    
    public float getBearing() {
        return this.ajt;
    }
    
    public LatLngBounds getBounds() {
        return this.ajH;
    }
    
    public float getHeight() {
        return this.ajG;
    }
    
    public BitmapDescriptor getImage() {
        return this.ajD;
    }
    
    public LatLng getLocation() {
        return this.ajE;
    }
    
    public float getTransparency() {
        return this.ajI;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public float getWidth() {
        return this.ajF;
    }
    
    public float getZIndex() {
        return this.ajA;
    }
    
    public GroundOverlayOptions image(final BitmapDescriptor ajD) {
        this.ajD = ajD;
        return this;
    }
    
    public boolean isVisible() {
        return this.ajB;
    }
    
    IBinder mM() {
        return this.ajD.mm().asBinder();
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n) {
        final boolean b = true;
        n.a(this.ajH == null, (Object)"Position has already been set using positionFromBounds");
        n.b(latLng != null, (Object)"Location must be specified");
        n.b(n >= 0.0f && b, (Object)"Width must be non-negative");
        return this.a(latLng, n, -1.0f);
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n, final float n2) {
        final boolean b = true;
        n.a(this.ajH == null, (Object)"Position has already been set using positionFromBounds");
        n.b(latLng != null, (Object)"Location must be specified");
        n.b(n >= 0.0f, (Object)"Width must be non-negative");
        n.b(n2 >= 0.0f && b, (Object)"Height must be non-negative");
        return this.a(latLng, n, n2);
    }
    
    public GroundOverlayOptions positionFromBounds(final LatLngBounds ajH) {
        n.a(this.ajE == null, (Object)("Position has already been set using position: " + this.ajE));
        this.ajH = ajH;
        return this;
    }
    
    public GroundOverlayOptions transparency(final float ajI) {
        n.b(ajI >= 0.0f && ajI <= 1.0f, (Object)"Transparency must be in the range [0..1]");
        this.ajI = ajI;
        return this;
    }
    
    public GroundOverlayOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            f.a(this, parcel, n);
            return;
        }
        e.a(this, parcel, n);
    }
    
    public GroundOverlayOptions zIndex(final float ajA) {
        this.ajA = ajA;
        return this;
    }
}
