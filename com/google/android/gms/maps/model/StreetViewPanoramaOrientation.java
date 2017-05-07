// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaOrientation implements SafeParcelable
{
    public static final StreetViewPanoramaOrientationCreator CREATOR;
    public final float bearing;
    public final float tilt;
    private final int xH;
    
    static {
        CREATOR = new StreetViewPanoramaOrientationCreator();
    }
    
    public StreetViewPanoramaOrientation(final float n, final float n2) {
        this(1, n, n2);
    }
    
    StreetViewPanoramaOrientation(final int xh, float n, final float n2) {
        fq.b(-90.0f <= n && n <= 90.0f, "Tilt needs to be between -90 and 90 inclusive");
        this.xH = xh;
        this.tilt = 0.0f + n;
        n = n2;
        if (n2 <= 0.0) {
            n = n2 % 360.0f + 360.0f;
        }
        this.bearing = n % 360.0f;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return new Builder(streetViewPanoramaOrientation);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaOrientation)) {
                return false;
            }
            final StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation)o;
            if (Float.floatToIntBits(this.tilt) != Float.floatToIntBits(streetViewPanoramaOrientation.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaOrientation.bearing)) {
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
        return fo.hashCode(this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StreetViewPanoramaOrientationCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        public float bearing;
        public float tilt;
        
        public Builder() {
        }
        
        public Builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.bearing = streetViewPanoramaOrientation.bearing;
            this.tilt = streetViewPanoramaOrientation.tilt;
        }
        
        public Builder bearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }
        
        public StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
        }
        
        public Builder tilt(final float tilt) {
            this.tilt = tilt;
            return this;
        }
    }
}
