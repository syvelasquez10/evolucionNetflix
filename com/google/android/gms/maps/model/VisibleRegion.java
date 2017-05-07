// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class VisibleRegion implements SafeParcelable
{
    public static final y CREATOR;
    private final int BR;
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;
    
    static {
        CREATOR = new y();
    }
    
    VisibleRegion(final int br, final LatLng nearLeft, final LatLng nearRight, final LatLng farLeft, final LatLng farRight, final LatLngBounds latLngBounds) {
        this.BR = br;
        this.nearLeft = nearLeft;
        this.nearRight = nearRight;
        this.farLeft = farLeft;
        this.farRight = farRight;
        this.latLngBounds = latLngBounds;
    }
    
    public VisibleRegion(final LatLng latLng, final LatLng latLng2, final LatLng latLng3, final LatLng latLng4, final LatLngBounds latLngBounds) {
        this(1, latLng, latLng2, latLng3, latLng4, latLngBounds);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof VisibleRegion)) {
                return false;
            }
            final VisibleRegion visibleRegion = (VisibleRegion)o;
            if (!this.nearLeft.equals(visibleRegion.nearLeft) || !this.nearRight.equals(visibleRegion.nearRight) || !this.farLeft.equals(visibleRegion.farLeft) || !this.farRight.equals(visibleRegion.farRight) || !this.latLngBounds.equals(visibleRegion.latLngBounds)) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("nearLeft", this.nearLeft).a("nearRight", this.nearRight).a("farLeft", this.farLeft).a("farRight", this.farRight).a("latLngBounds", this.latLngBounds).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            z.a(this, parcel, n);
            return;
        }
        y.a(this, parcel, n);
    }
}
