// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable
{
    public static final c CREATOR;
    private final int BR;
    private float ajA;
    private boolean ajB;
    private LatLng ajv;
    private double ajw;
    private float ajx;
    private int ajy;
    private int ajz;
    
    static {
        CREATOR = new c();
    }
    
    public CircleOptions() {
        this.ajv = null;
        this.ajw = 0.0;
        this.ajx = 10.0f;
        this.ajy = -16777216;
        this.ajz = 0;
        this.ajA = 0.0f;
        this.ajB = true;
        this.BR = 1;
    }
    
    CircleOptions(final int br, final LatLng ajv, final double ajw, final float ajx, final int ajy, final int ajz, final float ajA, final boolean ajB) {
        this.ajv = null;
        this.ajw = 0.0;
        this.ajx = 10.0f;
        this.ajy = -16777216;
        this.ajz = 0;
        this.ajA = 0.0f;
        this.ajB = true;
        this.BR = br;
        this.ajv = ajv;
        this.ajw = ajw;
        this.ajx = ajx;
        this.ajy = ajy;
        this.ajz = ajz;
        this.ajA = ajA;
        this.ajB = ajB;
    }
    
    public CircleOptions center(final LatLng ajv) {
        this.ajv = ajv;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CircleOptions fillColor(final int ajz) {
        this.ajz = ajz;
        return this;
    }
    
    public LatLng getCenter() {
        return this.ajv;
    }
    
    public int getFillColor() {
        return this.ajz;
    }
    
    public double getRadius() {
        return this.ajw;
    }
    
    public int getStrokeColor() {
        return this.ajy;
    }
    
    public float getStrokeWidth() {
        return this.ajx;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public float getZIndex() {
        return this.ajA;
    }
    
    public boolean isVisible() {
        return this.ajB;
    }
    
    public CircleOptions radius(final double ajw) {
        this.ajw = ajw;
        return this;
    }
    
    public CircleOptions strokeColor(final int ajy) {
        this.ajy = ajy;
        return this;
    }
    
    public CircleOptions strokeWidth(final float ajx) {
        this.ajx = ajx;
        return this;
    }
    
    public CircleOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            d.a(this, parcel, n);
            return;
        }
        c.a(this, parcel, n);
    }
    
    public CircleOptions zIndex(final float ajA) {
        this.ajA = ajA;
        return this;
    }
}
