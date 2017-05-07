// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LatLngBounds implements SafeParcelable
{
    public static final LatLngBoundsCreator CREATOR;
    public final LatLng northeast;
    public final LatLng southwest;
    private final int xH;
    
    static {
        CREATOR = new LatLngBoundsCreator();
    }
    
    LatLngBounds(final int xh, final LatLng southwest, final LatLng northeast) {
        fq.b(southwest, "null southwest");
        fq.b(northeast, "null northeast");
        fq.a(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", southwest.latitude, northeast.latitude);
        this.xH = xh;
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
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.southwest, this.northeast);
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
        return fo.e(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            d.a(this, parcel, n);
            return;
        }
        LatLngBoundsCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        private double Ta;
        private double Tb;
        private double Tc;
        private double Td;
        
        public Builder() {
            this.Ta = Double.POSITIVE_INFINITY;
            this.Tb = Double.NEGATIVE_INFINITY;
            this.Tc = Double.NaN;
            this.Td = Double.NaN;
        }
        
        private boolean d(final double n) {
            boolean b = false;
            if (this.Tc <= this.Td) {
                return this.Tc <= n && n <= this.Td;
            }
            if (this.Tc <= n || n <= this.Td) {
                b = true;
            }
            return b;
        }
        
        public LatLngBounds build() {
            fq.a(!Double.isNaN(this.Tc), (Object)"no included points");
            return new LatLngBounds(new LatLng(this.Ta, this.Tc), new LatLng(this.Tb, this.Td));
        }
        
        public Builder include(final LatLng latLng) {
            this.Ta = Math.min(this.Ta, latLng.latitude);
            this.Tb = Math.max(this.Tb, latLng.latitude);
            final double longitude = latLng.longitude;
            if (Double.isNaN(this.Tc)) {
                this.Tc = longitude;
                this.Td = longitude;
            }
            else if (!this.d(longitude)) {
                if (b(this.Tc, longitude) < c(this.Td, longitude)) {
                    this.Tc = longitude;
                    return this;
                }
                this.Td = longitude;
                return this;
            }
            return this;
        }
    }
}
