// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaOrientation implements SafeParcelable
{
    public static final t CREATOR;
    private final int BR;
    public final float bearing;
    public final float tilt;
    
    static {
        CREATOR = new t();
    }
    
    public StreetViewPanoramaOrientation(final float n, final float n2) {
        this(1, n, n2);
    }
    
    StreetViewPanoramaOrientation(final int br, float n, final float n2) {
        n.b(-90.0f <= n && n <= 90.0f, (Object)"Tilt needs to be between -90 and 90 inclusive");
        this.BR = br;
        this.tilt = 0.0f + n;
        n = n2;
        if (n2 <= 0.0) {
            n = n2 % 360.0f + 360.0f;
        }
        this.bearing = n % 360.0f;
    }
    
    public static StreetViewPanoramaOrientation$Builder builder() {
        return new StreetViewPanoramaOrientation$Builder();
    }
    
    public static StreetViewPanoramaOrientation$Builder builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return new StreetViewPanoramaOrientation$Builder(streetViewPanoramaOrientation);
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
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("tilt", this.tilt).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        t.a(this, parcel, n);
    }
}
