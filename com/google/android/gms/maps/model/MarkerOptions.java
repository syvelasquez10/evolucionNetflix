// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MarkerOptions implements SafeParcelable
{
    public static final MarkerOptionsCreator CREATOR;
    private String EB;
    private boolean SO;
    private float SW;
    private float SX;
    private LatLng Sn;
    private String Tf;
    private BitmapDescriptor Tg;
    private boolean Th;
    private boolean Ti;
    private float Tj;
    private float Tk;
    private float Tl;
    private float mAlpha;
    private final int xH;
    
    static {
        CREATOR = new MarkerOptionsCreator();
    }
    
    public MarkerOptions() {
        this.SW = 0.5f;
        this.SX = 1.0f;
        this.SO = true;
        this.Ti = false;
        this.Tj = 0.0f;
        this.Tk = 0.5f;
        this.Tl = 0.0f;
        this.mAlpha = 1.0f;
        this.xH = 1;
    }
    
    MarkerOptions(final int xh, final LatLng sn, final String eb, final String tf, final IBinder binder, final float sw, final float sx, final boolean th, final boolean so, final boolean ti, final float tj, final float tk, final float tl, final float mAlpha) {
        this.SW = 0.5f;
        this.SX = 1.0f;
        this.SO = true;
        this.Ti = false;
        this.Tj = 0.0f;
        this.Tk = 0.5f;
        this.Tl = 0.0f;
        this.mAlpha = 1.0f;
        this.xH = xh;
        this.Sn = sn;
        this.EB = eb;
        this.Tf = tf;
        BitmapDescriptor tg;
        if (binder == null) {
            tg = null;
        }
        else {
            tg = new BitmapDescriptor(d.a.K(binder));
        }
        this.Tg = tg;
        this.SW = sw;
        this.SX = sx;
        this.Th = th;
        this.SO = so;
        this.Ti = ti;
        this.Tj = tj;
        this.Tk = tk;
        this.Tl = tl;
        this.mAlpha = mAlpha;
    }
    
    public MarkerOptions alpha(final float mAlpha) {
        this.mAlpha = mAlpha;
        return this;
    }
    
    public MarkerOptions anchor(final float sw, final float sx) {
        this.SW = sw;
        this.SX = sx;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MarkerOptions draggable(final boolean th) {
        this.Th = th;
        return this;
    }
    
    public MarkerOptions flat(final boolean ti) {
        this.Ti = ti;
        return this;
    }
    
    public float getAlpha() {
        return this.mAlpha;
    }
    
    public float getAnchorU() {
        return this.SW;
    }
    
    public float getAnchorV() {
        return this.SX;
    }
    
    public BitmapDescriptor getIcon() {
        return this.Tg;
    }
    
    public float getInfoWindowAnchorU() {
        return this.Tk;
    }
    
    public float getInfoWindowAnchorV() {
        return this.Tl;
    }
    
    public LatLng getPosition() {
        return this.Sn;
    }
    
    public float getRotation() {
        return this.Tj;
    }
    
    public String getSnippet() {
        return this.Tf;
    }
    
    public String getTitle() {
        return this.EB;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    IBinder iE() {
        if (this.Tg == null) {
            return null;
        }
        return this.Tg.id().asBinder();
    }
    
    public MarkerOptions icon(final BitmapDescriptor tg) {
        this.Tg = tg;
        return this;
    }
    
    public MarkerOptions infoWindowAnchor(final float tk, final float tl) {
        this.Tk = tk;
        this.Tl = tl;
        return this;
    }
    
    public boolean isDraggable() {
        return this.Th;
    }
    
    public boolean isFlat() {
        return this.Ti;
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public MarkerOptions position(final LatLng sn) {
        this.Sn = sn;
        return this;
    }
    
    public MarkerOptions rotation(final float tj) {
        this.Tj = tj;
        return this;
    }
    
    public MarkerOptions snippet(final String tf) {
        this.Tf = tf;
        return this;
    }
    
    public MarkerOptions title(final String eb) {
        this.EB = eb;
        return this;
    }
    
    public MarkerOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            f.a(this, parcel, n);
            return;
        }
        MarkerOptionsCreator.a(this, parcel, n);
    }
}
