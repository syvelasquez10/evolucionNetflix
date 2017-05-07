// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import com.google.android.gms.dynamic.b;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MarkerOptions implements SafeParcelable
{
    public static final MarkerOptionsCreator CREATOR;
    private float CF;
    private float CG;
    private LatLng CM;
    private String CN;
    private BitmapDescriptor CO;
    private boolean CP;
    private boolean CQ;
    private float CR;
    private float CS;
    private float CT;
    private boolean Cx;
    private final int kg;
    private float mAlpha;
    private String qL;
    
    static {
        CREATOR = new MarkerOptionsCreator();
    }
    
    public MarkerOptions() {
        this.CF = 0.5f;
        this.CG = 1.0f;
        this.Cx = true;
        this.CQ = false;
        this.CR = 0.0f;
        this.CS = 0.5f;
        this.CT = 0.0f;
        this.mAlpha = 1.0f;
        this.kg = 1;
    }
    
    MarkerOptions(final int kg, final LatLng cm, final String ql, final String cn, final IBinder binder, final float cf, final float cg, final boolean cp, final boolean cx, final boolean cq, final float cr, final float cs, final float ct, final float mAlpha) {
        this.CF = 0.5f;
        this.CG = 1.0f;
        this.Cx = true;
        this.CQ = false;
        this.CR = 0.0f;
        this.CS = 0.5f;
        this.CT = 0.0f;
        this.mAlpha = 1.0f;
        this.kg = kg;
        this.CM = cm;
        this.qL = ql;
        this.CN = cn;
        BitmapDescriptor co;
        if (binder == null) {
            co = null;
        }
        else {
            co = new BitmapDescriptor(b.a.E(binder));
        }
        this.CO = co;
        this.CF = cf;
        this.CG = cg;
        this.CP = cp;
        this.Cx = cx;
        this.CQ = cq;
        this.CR = cr;
        this.CS = cs;
        this.CT = ct;
        this.mAlpha = mAlpha;
    }
    
    public MarkerOptions alpha(final float mAlpha) {
        this.mAlpha = mAlpha;
        return this;
    }
    
    public MarkerOptions anchor(final float cf, final float cg) {
        this.CF = cf;
        this.CG = cg;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MarkerOptions draggable(final boolean cp) {
        this.CP = cp;
        return this;
    }
    
    IBinder eG() {
        if (this.CO == null) {
            return null;
        }
        return this.CO.el().asBinder();
    }
    
    public MarkerOptions flat(final boolean cq) {
        this.CQ = cq;
        return this;
    }
    
    public float getAlpha() {
        return this.mAlpha;
    }
    
    public float getAnchorU() {
        return this.CF;
    }
    
    public float getAnchorV() {
        return this.CG;
    }
    
    public BitmapDescriptor getIcon() {
        return this.CO;
    }
    
    public float getInfoWindowAnchorU() {
        return this.CS;
    }
    
    public float getInfoWindowAnchorV() {
        return this.CT;
    }
    
    public LatLng getPosition() {
        return this.CM;
    }
    
    public float getRotation() {
        return this.CR;
    }
    
    public String getSnippet() {
        return this.CN;
    }
    
    public String getTitle() {
        return this.qL;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public MarkerOptions icon(final BitmapDescriptor co) {
        this.CO = co;
        return this;
    }
    
    public MarkerOptions infoWindowAnchor(final float cs, final float ct) {
        this.CS = cs;
        this.CT = ct;
        return this;
    }
    
    public boolean isDraggable() {
        return this.CP;
    }
    
    public boolean isFlat() {
        return this.CQ;
    }
    
    public boolean isVisible() {
        return this.Cx;
    }
    
    public MarkerOptions position(final LatLng cm) {
        this.CM = cm;
        return this;
    }
    
    public MarkerOptions rotation(final float cr) {
        this.CR = cr;
        return this;
    }
    
    public MarkerOptions snippet(final String cn) {
        this.CN = cn;
        return this;
    }
    
    public MarkerOptions title(final String ql) {
        this.qL = ql;
        return this;
    }
    
    public MarkerOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            f.a(this, parcel, n);
            return;
        }
        MarkerOptionsCreator.a(this, parcel, n);
    }
}
