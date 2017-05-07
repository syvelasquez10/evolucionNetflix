// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLocation implements SafeParcelable
{
    public static final s CREATOR;
    private final int BR;
    public final StreetViewPanoramaLink[] links;
    public final String panoId;
    public final LatLng position;
    
    static {
        CREATOR = new s();
    }
    
    StreetViewPanoramaLocation(final int br, final StreetViewPanoramaLink[] links, final LatLng position, final String panoId) {
        this.BR = br;
        this.links = links;
        this.position = position;
        this.panoId = panoId;
    }
    
    public StreetViewPanoramaLocation(final StreetViewPanoramaLink[] array, final LatLng latLng, final String s) {
        this(1, array, latLng, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaLocation)) {
                return false;
            }
            final StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation)o;
            if (!this.panoId.equals(streetViewPanoramaLocation.panoId) || !this.position.equals(streetViewPanoramaLocation.position)) {
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
        return m.hashCode(this.position, this.panoId);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("panoId", this.panoId).a("position", this.position.toString()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        s.a(this, parcel, n);
    }
}
