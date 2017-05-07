// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable
{
    public static final CircleOptionsCreator CREATOR;
    private LatLng Cr;
    private double Cs;
    private float Ct;
    private int Cu;
    private int Cv;
    private float Cw;
    private boolean Cx;
    private final int kg;
    
    static {
        CREATOR = new CircleOptionsCreator();
    }
    
    public CircleOptions() {
        this.Cr = null;
        this.Cs = 0.0;
        this.Ct = 10.0f;
        this.Cu = -16777216;
        this.Cv = 0;
        this.Cw = 0.0f;
        this.Cx = true;
        this.kg = 1;
    }
    
    CircleOptions(final int kg, final LatLng cr, final double cs, final float ct, final int cu, final int cv, final float cw, final boolean cx) {
        this.Cr = null;
        this.Cs = 0.0;
        this.Ct = 10.0f;
        this.Cu = -16777216;
        this.Cv = 0;
        this.Cw = 0.0f;
        this.Cx = true;
        this.kg = kg;
        this.Cr = cr;
        this.Cs = cs;
        this.Ct = ct;
        this.Cu = cu;
        this.Cv = cv;
        this.Cw = cw;
        this.Cx = cx;
    }
    
    public CircleOptions center(final LatLng cr) {
        this.Cr = cr;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CircleOptions fillColor(final int cv) {
        this.Cv = cv;
        return this;
    }
    
    public LatLng getCenter() {
        return this.Cr;
    }
    
    public int getFillColor() {
        return this.Cv;
    }
    
    public double getRadius() {
        return this.Cs;
    }
    
    public int getStrokeColor() {
        return this.Cu;
    }
    
    public float getStrokeWidth() {
        return this.Ct;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public float getZIndex() {
        return this.Cw;
    }
    
    public boolean isVisible() {
        return this.Cx;
    }
    
    public CircleOptions radius(final double cs) {
        this.Cs = cs;
        return this;
    }
    
    public CircleOptions strokeColor(final int cu) {
        this.Cu = cu;
        return this;
    }
    
    public CircleOptions strokeWidth(final float ct) {
        this.Ct = ct;
        return this;
    }
    
    public CircleOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            b.a(this, parcel, n);
            return;
        }
        CircleOptionsCreator.a(this, parcel, n);
    }
    
    public CircleOptions zIndex(final float cw) {
        this.Cw = cw;
        return this;
    }
}
