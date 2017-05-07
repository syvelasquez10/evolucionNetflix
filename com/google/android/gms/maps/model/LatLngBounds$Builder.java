// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.n;

public final class LatLngBounds$Builder
{
    private double ajN;
    private double ajO;
    private double ajP;
    private double ajQ;
    
    public LatLngBounds$Builder() {
        this.ajN = Double.POSITIVE_INFINITY;
        this.ajO = Double.NEGATIVE_INFINITY;
        this.ajP = Double.NaN;
        this.ajQ = Double.NaN;
    }
    
    private boolean d(final double n) {
        boolean b = false;
        if (this.ajP <= this.ajQ) {
            return this.ajP <= n && n <= this.ajQ;
        }
        if (this.ajP <= n || n <= this.ajQ) {
            b = true;
        }
        return b;
    }
    
    public LatLngBounds build() {
        n.a(!Double.isNaN(this.ajP), (Object)"no included points");
        return new LatLngBounds(new LatLng(this.ajN, this.ajP), new LatLng(this.ajO, this.ajQ));
    }
    
    public LatLngBounds$Builder include(final LatLng latLng) {
        this.ajN = Math.min(this.ajN, latLng.latitude);
        this.ajO = Math.max(this.ajO, latLng.latitude);
        final double longitude = latLng.longitude;
        if (Double.isNaN(this.ajP)) {
            this.ajP = longitude;
            this.ajQ = longitude;
        }
        else if (!this.d(longitude)) {
            if (b(this.ajP, longitude) < c(this.ajQ, longitude)) {
                this.ajP = longitude;
                return this;
            }
            this.ajQ = longitude;
            return this;
        }
        return this;
    }
}
