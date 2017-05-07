// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

public final class StreetViewPanoramaCamera$Builder
{
    public float bearing;
    public float tilt;
    public float zoom;
    
    public StreetViewPanoramaCamera$Builder() {
    }
    
    public StreetViewPanoramaCamera$Builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zoom = streetViewPanoramaCamera.zoom;
        this.bearing = streetViewPanoramaCamera.bearing;
        this.tilt = streetViewPanoramaCamera.tilt;
    }
    
    public StreetViewPanoramaCamera$Builder bearing(final float bearing) {
        this.bearing = bearing;
        return this;
    }
    
    public StreetViewPanoramaCamera build() {
        return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
    }
    
    public StreetViewPanoramaCamera$Builder orientation(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.tilt = streetViewPanoramaOrientation.tilt;
        this.bearing = streetViewPanoramaOrientation.bearing;
        return this;
    }
    
    public StreetViewPanoramaCamera$Builder tilt(final float tilt) {
        this.tilt = tilt;
        return this;
    }
    
    public StreetViewPanoramaCamera$Builder zoom(final float zoom) {
        this.zoom = zoom;
        return this;
    }
}
