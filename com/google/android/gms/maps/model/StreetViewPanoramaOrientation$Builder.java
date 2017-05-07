// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

public final class StreetViewPanoramaOrientation$Builder
{
    public float bearing;
    public float tilt;
    
    public StreetViewPanoramaOrientation$Builder() {
    }
    
    public StreetViewPanoramaOrientation$Builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.bearing = streetViewPanoramaOrientation.bearing;
        this.tilt = streetViewPanoramaOrientation.tilt;
    }
    
    public StreetViewPanoramaOrientation$Builder bearing(final float bearing) {
        this.bearing = bearing;
        return this;
    }
    
    public StreetViewPanoramaOrientation build() {
        return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
    }
    
    public StreetViewPanoramaOrientation$Builder tilt(final float tilt) {
        this.tilt = tilt;
        return this;
    }
}
