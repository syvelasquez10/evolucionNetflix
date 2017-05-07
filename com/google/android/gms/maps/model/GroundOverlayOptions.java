// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import com.google.android.gms.internal.eg;
import com.google.android.gms.dynamic.b;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GroundOverlayOptions implements SafeParcelable
{
    public static final GroundOverlayOptionsCreator CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    private LatLng CA;
    private float CB;
    private float CC;
    private LatLngBounds CD;
    private float CE;
    private float CF;
    private float CG;
    private float Cp;
    private float Cw;
    private boolean Cx;
    private BitmapDescriptor Cz;
    private final int kg;
    
    static {
        CREATOR = new GroundOverlayOptionsCreator();
    }
    
    public GroundOverlayOptions() {
        this.Cx = true;
        this.CE = 0.0f;
        this.CF = 0.5f;
        this.CG = 0.5f;
        this.kg = 1;
    }
    
    GroundOverlayOptions(final int kg, final IBinder binder, final LatLng ca, final float cb, final float cc, final LatLngBounds cd, final float cp, final float cw, final boolean cx, final float ce, final float cf, final float cg) {
        this.Cx = true;
        this.CE = 0.0f;
        this.CF = 0.5f;
        this.CG = 0.5f;
        this.kg = kg;
        this.Cz = new BitmapDescriptor(b.a.E(binder));
        this.CA = ca;
        this.CB = cb;
        this.CC = cc;
        this.CD = cd;
        this.Cp = cp;
        this.Cw = cw;
        this.Cx = cx;
        this.CE = ce;
        this.CF = cf;
        this.CG = cg;
    }
    
    private GroundOverlayOptions a(final LatLng ca, final float cb, final float cc) {
        this.CA = ca;
        this.CB = cb;
        this.CC = cc;
        return this;
    }
    
    public GroundOverlayOptions anchor(final float cf, final float cg) {
        this.CF = cf;
        this.CG = cg;
        return this;
    }
    
    public GroundOverlayOptions bearing(final float n) {
        this.Cp = (n % 360.0f + 360.0f) % 360.0f;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    IBinder eF() {
        return this.Cz.el().asBinder();
    }
    
    public float getAnchorU() {
        return this.CF;
    }
    
    public float getAnchorV() {
        return this.CG;
    }
    
    public float getBearing() {
        return this.Cp;
    }
    
    public LatLngBounds getBounds() {
        return this.CD;
    }
    
    public float getHeight() {
        return this.CC;
    }
    
    public BitmapDescriptor getImage() {
        return this.Cz;
    }
    
    public LatLng getLocation() {
        return this.CA;
    }
    
    public float getTransparency() {
        return this.CE;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public float getWidth() {
        return this.CB;
    }
    
    public float getZIndex() {
        return this.Cw;
    }
    
    public GroundOverlayOptions image(final BitmapDescriptor cz) {
        this.Cz = cz;
        return this;
    }
    
    public boolean isVisible() {
        return this.Cx;
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n) {
        final boolean b = true;
        eg.a(this.CD == null, (Object)"Position has already been set using positionFromBounds");
        eg.b(latLng != null, "Location must be specified");
        eg.b(n >= 0.0f && b, "Width must be non-negative");
        return this.a(latLng, n, -1.0f);
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n, final float n2) {
        final boolean b = true;
        eg.a(this.CD == null, (Object)"Position has already been set using positionFromBounds");
        eg.b(latLng != null, "Location must be specified");
        eg.b(n >= 0.0f, "Width must be non-negative");
        eg.b(n2 >= 0.0f && b, "Height must be non-negative");
        return this.a(latLng, n, n2);
    }
    
    public GroundOverlayOptions positionFromBounds(final LatLngBounds cd) {
        eg.a(this.CA == null, (Object)("Position has already been set using position: " + this.CA));
        this.CD = cd;
        return this;
    }
    
    public GroundOverlayOptions transparency(final float ce) {
        eg.b(ce >= 0.0f && ce <= 1.0f, "Transparency must be in the range [0..1]");
        this.CE = ce;
        return this;
    }
    
    public GroundOverlayOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            c.a(this, parcel, n);
            return;
        }
        GroundOverlayOptionsCreator.a(this, parcel, n);
    }
    
    public GroundOverlayOptions zIndex(final float cw) {
        this.Cw = cw;
        return this;
    }
}
