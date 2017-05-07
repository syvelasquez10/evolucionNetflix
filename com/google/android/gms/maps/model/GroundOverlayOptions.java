// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.internal.fq;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class GroundOverlayOptions implements SafeParcelable
{
    public static final GroundOverlayOptionsCreator CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    private float SG;
    private float SN;
    private boolean SO;
    private BitmapDescriptor SQ;
    private LatLng SR;
    private float SS;
    private float ST;
    private LatLngBounds SU;
    private float SV;
    private float SW;
    private float SX;
    private final int xH;
    
    static {
        CREATOR = new GroundOverlayOptionsCreator();
    }
    
    public GroundOverlayOptions() {
        this.SO = true;
        this.SV = 0.0f;
        this.SW = 0.5f;
        this.SX = 0.5f;
        this.xH = 1;
    }
    
    GroundOverlayOptions(final int xh, final IBinder binder, final LatLng sr, final float ss, final float st, final LatLngBounds su, final float sg, final float sn, final boolean so, final float sv, final float sw, final float sx) {
        this.SO = true;
        this.SV = 0.0f;
        this.SW = 0.5f;
        this.SX = 0.5f;
        this.xH = xh;
        this.SQ = new BitmapDescriptor(d.a.K(binder));
        this.SR = sr;
        this.SS = ss;
        this.ST = st;
        this.SU = su;
        this.SG = sg;
        this.SN = sn;
        this.SO = so;
        this.SV = sv;
        this.SW = sw;
        this.SX = sx;
    }
    
    private GroundOverlayOptions a(final LatLng sr, final float ss, final float st) {
        this.SR = sr;
        this.SS = ss;
        this.ST = st;
        return this;
    }
    
    public GroundOverlayOptions anchor(final float sw, final float sx) {
        this.SW = sw;
        this.SX = sx;
        return this;
    }
    
    public GroundOverlayOptions bearing(final float n) {
        this.SG = (n % 360.0f + 360.0f) % 360.0f;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public float getAnchorU() {
        return this.SW;
    }
    
    public float getAnchorV() {
        return this.SX;
    }
    
    public float getBearing() {
        return this.SG;
    }
    
    public LatLngBounds getBounds() {
        return this.SU;
    }
    
    public float getHeight() {
        return this.ST;
    }
    
    public BitmapDescriptor getImage() {
        return this.SQ;
    }
    
    public LatLng getLocation() {
        return this.SR;
    }
    
    public float getTransparency() {
        return this.SV;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public float getWidth() {
        return this.SS;
    }
    
    public float getZIndex() {
        return this.SN;
    }
    
    IBinder iD() {
        return this.SQ.id().asBinder();
    }
    
    public GroundOverlayOptions image(final BitmapDescriptor sq) {
        this.SQ = sq;
        return this;
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n) {
        final boolean b = true;
        fq.a(this.SU == null, (Object)"Position has already been set using positionFromBounds");
        fq.b(latLng != null, "Location must be specified");
        fq.b(n >= 0.0f && b, "Width must be non-negative");
        return this.a(latLng, n, -1.0f);
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n, final float n2) {
        final boolean b = true;
        fq.a(this.SU == null, (Object)"Position has already been set using positionFromBounds");
        fq.b(latLng != null, "Location must be specified");
        fq.b(n >= 0.0f, "Width must be non-negative");
        fq.b(n2 >= 0.0f && b, "Height must be non-negative");
        return this.a(latLng, n, n2);
    }
    
    public GroundOverlayOptions positionFromBounds(final LatLngBounds su) {
        fq.a(this.SR == null, (Object)("Position has already been set using position: " + this.SR));
        this.SU = su;
        return this;
    }
    
    public GroundOverlayOptions transparency(final float sv) {
        fq.b(sv >= 0.0f && sv <= 1.0f, "Transparency must be in the range [0..1]");
        this.SV = sv;
        return this;
    }
    
    public GroundOverlayOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            c.a(this, parcel, n);
            return;
        }
        GroundOverlayOptionsCreator.a(this, parcel, n);
    }
    
    public GroundOverlayOptions zIndex(final float sn) {
        this.SN = sn;
        return this;
    }
}
