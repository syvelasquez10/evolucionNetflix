// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLink implements SafeParcelable
{
    public static final r CREATOR;
    private final int BR;
    public final float bearing;
    public final String panoId;
    
    static {
        CREATOR = new r();
    }
    
    StreetViewPanoramaLink(final int br, final String panoId, final float n) {
        this.BR = br;
        this.panoId = panoId;
        float n2 = n;
        if (n <= 0.0) {
            n2 = n % 360.0f + 360.0f;
        }
        this.bearing = n2 % 360.0f;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaLink)) {
                return false;
            }
            final StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink)o;
            if (!this.panoId.equals(streetViewPanoramaLink.panoId) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaLink.bearing)) {
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
        return m.hashCode(this.panoId, this.bearing);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("panoId", this.panoId).a("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        r.a(this, parcel, n);
    }
}
