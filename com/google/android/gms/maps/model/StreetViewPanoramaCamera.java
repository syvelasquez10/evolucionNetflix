// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.internal.fq;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaCamera implements SafeParcelable
{
    public static final StreetViewPanoramaCameraCreator CREATOR;
    private StreetViewPanoramaOrientation Tr;
    public final float bearing;
    public final float tilt;
    private final int xH;
    public final float zoom;
    
    static {
        CREATOR = new StreetViewPanoramaCameraCreator();
    }
    
    public StreetViewPanoramaCamera(final float n, final float n2, final float n3) {
        this(1, n, n2, n3);
    }
    
    StreetViewPanoramaCamera(final int xh, float zoom, final float n, final float n2) {
        fq.b(-90.0f <= n && n <= 90.0f, "Tilt needs to be between -90 and 90 inclusive");
        this.xH = xh;
        this.zoom = zoom;
        this.tilt = 0.0f + n;
        if (n2 <= 0.0) {
            zoom = n2 % 360.0f + 360.0f;
        }
        else {
            zoom = n2;
        }
        this.bearing = zoom % 360.0f;
        this.Tr = new StreetViewPanoramaOrientation.Builder().tilt(n).bearing(n2).build();
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new Builder(streetViewPanoramaCamera);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaCamera)) {
                return false;
            }
            final StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera)o;
            if (Float.floatToIntBits(this.zoom) != Float.floatToIntBits(streetViewPanoramaCamera.zoom) || Float.floatToIntBits(this.tilt) != Float.floatToIntBits(streetViewPanoramaCamera.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaCamera.bearing)) {
                return false;
            }
        }
        return true;
    }
    
    public StreetViewPanoramaOrientation getOrientation() {
        return this.Tr;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("zoom", this.zoom).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StreetViewPanoramaCameraCreator.a(this, parcel, n);
    }
    
    public static final class Builder
    {
        public float bearing;
        public float tilt;
        public float zoom;
        
        public Builder() {
        }
        
        public Builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
            this.zoom = streetViewPanoramaCamera.zoom;
            this.bearing = streetViewPanoramaCamera.bearing;
            this.tilt = streetViewPanoramaCamera.tilt;
        }
        
        public Builder bearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }
        
        public StreetViewPanoramaCamera build() {
            return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
        }
        
        public Builder orientation(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.tilt = streetViewPanoramaOrientation.tilt;
            this.bearing = streetViewPanoramaOrientation.bearing;
            return this;
        }
        
        public Builder tilt(final float tilt) {
            this.tilt = tilt;
            return this;
        }
        
        public Builder zoom(final float zoom) {
            this.zoom = zoom;
            return this;
        }
    }
}
