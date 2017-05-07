// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LatLngBounds implements SafeParcelable
{
    public static final LatLngBoundsCreator CREATOR;
    private final int kg;
    public final LatLng northeast;
    public final LatLng southwest;
    
    static {
        CREATOR = new LatLngBoundsCreator();
    }
    
    LatLngBounds(final int kg, final LatLng southwest, final LatLng northeast) {
        eg.b(southwest, "null southwest");
        eg.b(northeast, "null northeast");
        eg.a(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", southwest.latitude, northeast.latitude);
        this.kg = kg;
        this.southwest = southwest;
        this.northeast = northeast;
    }
    
    public LatLngBounds(final LatLng latLng, final LatLng latLng2) {
        this(1, latLng, latLng2);
    }
    
    private static double b(final double n, final double n2) {
        return (n - n2 + 360.0) % 360.0;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    private static double c(final double n, final double n2) {
        return (n2 - n + 360.0) % 360.0;
    }
    
    private boolean c(final double n) {
        return this.southwest.latitude <= n && n <= this.northeast.latitude;
    }
    
    private boolean d(final double n) {
        boolean b = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= n && n <= this.northeast.longitude;
        }
        if (this.southwest.longitude <= n || n <= this.northeast.longitude) {
            b = true;
        }
        return b;
    }
    
    public boolean contains(final LatLng latLng) {
        return this.c(latLng.latitude) && this.d(latLng.longitude);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LatLngBounds)) {
                return false;
            }
            final LatLngBounds latLngBounds = (LatLngBounds)o;
            if (!this.southwest.equals(latLngBounds.southwest) || !this.northeast.equals(latLngBounds.northeast)) {
                return false;
            }
        }
        return true;
    }
    
    public LatLng getCenter() {
        final double n = (this.southwest.latitude + this.northeast.latitude) / 2.0;
        final double longitude = this.northeast.longitude;
        final double longitude2 = this.southwest.longitude;
        double n2;
        if (longitude2 <= longitude) {
            n2 = (longitude + longitude2) / 2.0;
        }
        else {
            n2 = (longitude + 360.0 + longitude2) / 2.0;
        }
        return new LatLng(n, n2);
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.southwest, this.northeast);
    }
    
    public LatLngBounds including(final LatLng latLng) {
        final double min = Math.min(this.southwest.latitude, latLng.latitude);
        final double max = Math.max(this.northeast.latitude, latLng.latitude);
        double longitude = this.northeast.longitude;
        final double longitude2 = this.southwest.longitude;
        double longitude3 = latLng.longitude;
        if (!this.d(longitude3)) {
            if (b(longitude2, longitude3) >= c(longitude, longitude3)) {
                longitude = longitude3;
                longitude3 = longitude2;
            }
        }
        else {
            longitude3 = longitude2;
        }
        return new LatLngBounds(new LatLng(min, longitude3), new LatLng(max, longitude));
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            d.a(this, parcel, n);
            return;
        }
        LatLngBoundsCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        private double CH;
        private double CI;
        private double CJ;
        private double CK;
        
        public Builder() {
            this.CH = Double.POSITIVE_INFINITY;
            this.CI = Double.NEGATIVE_INFINITY;
            this.CJ = Double.NaN;
            this.CK = Double.NaN;
        }
        
        private boolean d(final double n) {
            boolean b = false;
            if (this.CJ <= this.CK) {
                return this.CJ <= n && n <= this.CK;
            }
            if (this.CJ <= n || n <= this.CK) {
                b = true;
            }
            return b;
        }
        
        public LatLngBounds build() {
            eg.a(!Double.isNaN(this.CJ), (Object)"no included points");
            return new LatLngBounds(new LatLng(this.CH, this.CJ), new LatLng(this.CI, this.CK));
        }
        
        public Builder include(final LatLng latLng) {
            this.CH = Math.min(this.CH, latLng.latitude);
            this.CI = Math.max(this.CI, latLng.latitude);
            final double longitude = latLng.longitude;
            if (Double.isNaN(this.CJ)) {
                this.CJ = longitude;
                this.CK = longitude;
            }
            else if (!this.d(longitude)) {
                if (b(this.CJ, longitude) < c(this.CK, longitude)) {
                    this.CJ = longitude;
                    return this;
                }
                this.CK = longitude;
                return this;
            }
            return this;
        }
    }
}
