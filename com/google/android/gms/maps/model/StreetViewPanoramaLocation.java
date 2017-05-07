// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class StreetViewPanoramaLocation implements SafeParcelable
{
    public static final StreetViewPanoramaLocationCreator CREATOR;
    public final StreetViewPanoramaLink[] links;
    public final String panoId;
    public final LatLng position;
    private final int xH;
    
    static {
        CREATOR = new StreetViewPanoramaLocationCreator();
    }
    
    StreetViewPanoramaLocation(final int xh, final StreetViewPanoramaLink[] links, final LatLng position, final String panoId) {
        this.xH = xh;
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
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.position, this.panoId);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("panoId", this.panoId).a("position", this.position.toString()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        StreetViewPanoramaLocationCreator.a(this, parcel, n);
    }
}
