// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class VisibleRegion implements SafeParcelable
{
    public static final VisibleRegionCreator CREATOR;
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;
    private final int xH;
    
    static {
        CREATOR = new VisibleRegionCreator();
    }
    
    VisibleRegion(final int xh, final LatLng nearLeft, final LatLng nearRight, final LatLng farLeft, final LatLng farRight, final LatLngBounds latLngBounds) {
        this.xH = xh;
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
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("nearLeft", this.nearLeft).a("nearRight", this.nearRight).a("farLeft", this.farLeft).a("farRight", this.farRight).a("latLngBounds", this.latLngBounds).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            k.a(this, parcel, n);
            return;
        }
        VisibleRegionCreator.a(this, parcel, n);
    }
}
