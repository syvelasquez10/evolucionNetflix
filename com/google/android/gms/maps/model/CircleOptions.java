// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable
{
    public static final CircleOptionsCreator CREATOR;
    private LatLng SI;
    private double SJ;
    private float SK;
    private int SL;
    private int SM;
    private float SN;
    private boolean SO;
    private final int xH;
    
    static {
        CREATOR = new CircleOptionsCreator();
    }
    
    public CircleOptions() {
        this.SI = null;
        this.SJ = 0.0;
        this.SK = 10.0f;
        this.SL = -16777216;
        this.SM = 0;
        this.SN = 0.0f;
        this.SO = true;
        this.xH = 1;
    }
    
    CircleOptions(final int xh, final LatLng si, final double sj, final float sk, final int sl, final int sm, final float sn, final boolean so) {
        this.SI = null;
        this.SJ = 0.0;
        this.SK = 10.0f;
        this.SL = -16777216;
        this.SM = 0;
        this.SN = 0.0f;
        this.SO = true;
        this.xH = xh;
        this.SI = si;
        this.SJ = sj;
        this.SK = sk;
        this.SL = sl;
        this.SM = sm;
        this.SN = sn;
        this.SO = so;
    }
    
    public CircleOptions center(final LatLng si) {
        this.SI = si;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CircleOptions fillColor(final int sm) {
        this.SM = sm;
        return this;
    }
    
    public LatLng getCenter() {
        return this.SI;
    }
    
    public int getFillColor() {
        return this.SM;
    }
    
    public double getRadius() {
        return this.SJ;
    }
    
    public int getStrokeColor() {
        return this.SL;
    }
    
    public float getStrokeWidth() {
        return this.SK;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public float getZIndex() {
        return this.SN;
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public CircleOptions radius(final double sj) {
        this.SJ = sj;
        return this;
    }
    
    public CircleOptions strokeColor(final int sl) {
        this.SL = sl;
        return this;
    }
    
    public CircleOptions strokeWidth(final float sk) {
        this.SK = sk;
        return this;
    }
    
    public CircleOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            b.a(this, parcel, n);
            return;
        }
        CircleOptionsCreator.a(this, parcel, n);
    }
    
    public CircleOptions zIndex(final float sn) {
        this.SN = sn;
        return this;
    }
}
