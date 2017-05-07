// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaCamera implements SafeParcelable
{
    public static final q CREATOR;
    private final int BR;
    private StreetViewPanoramaOrientation ake;
    public final float bearing;
    public final float tilt;
    public final float zoom;
    
    static {
        CREATOR = new q();
    }
    
    public StreetViewPanoramaCamera(final float n, final float n2, final float n3) {
        this(1, n, n2, n3);
    }
    
    StreetViewPanoramaCamera(final int br, float zoom, final float n, final float n2) {
        n.b(-90.0f <= n && n <= 90.0f, (Object)"Tilt needs to be between -90 and 90 inclusive");
        this.BR = br;
        this.zoom = zoom;
        this.tilt = 0.0f + n;
        if (n2 <= 0.0) {
            zoom = n2 % 360.0f + 360.0f;
        }
        else {
            zoom = n2;
        }
        this.bearing = zoom % 360.0f;
        this.ake = new StreetViewPanoramaOrientation$Builder().tilt(n).bearing(n2).build();
    }
    
    public static StreetViewPanoramaCamera$Builder builder() {
        return new StreetViewPanoramaCamera$Builder();
    }
    
    public static StreetViewPanoramaCamera$Builder builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new StreetViewPanoramaCamera$Builder(streetViewPanoramaCamera);
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
        return this.ake;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("zoom", this.zoom).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        q.a(this, parcel, n);
    }
}
