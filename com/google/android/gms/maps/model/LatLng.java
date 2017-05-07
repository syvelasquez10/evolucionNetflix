// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LatLng implements SafeParcelable
{
    public static final LatLngCreator CREATOR;
    public final double latitude;
    public final double longitude;
    private final int xH;
    
    static {
        CREATOR = new LatLngCreator();
    }
    
    public LatLng(final double n, final double n2) {
        this(1, n, n2);
    }
    
    LatLng(final int xh, final double n, final double longitude) {
        this.xH = xh;
        if (-180.0 <= longitude && longitude < 180.0) {
            this.longitude = longitude;
        }
        else {
            this.longitude = ((longitude - 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
        }
        this.latitude = Math.max(-90.0, Math.min(90.0, n));
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LatLng)) {
                return false;
            }
            final LatLng latLng = (LatLng)o;
            if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(latLng.latitude) || Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(latLng.longitude)) {
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
        final long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32);
    }
    
    @Override
    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            e.a(this, parcel, n);
            return;
        }
        LatLngCreator.a(this, parcel, n);
    }
}
