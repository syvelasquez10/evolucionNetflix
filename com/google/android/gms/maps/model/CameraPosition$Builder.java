// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

public final class CameraPosition$Builder
{
    private LatLng ajq;
    private float ajr;
    private float ajs;
    private float ajt;
    
    public CameraPosition$Builder() {
    }
    
    public CameraPosition$Builder(final CameraPosition cameraPosition) {
        this.ajq = cameraPosition.target;
        this.ajr = cameraPosition.zoom;
        this.ajs = cameraPosition.tilt;
        this.ajt = cameraPosition.bearing;
    }
    
    public CameraPosition$Builder bearing(final float ajt) {
        this.ajt = ajt;
        return this;
    }
    
    public CameraPosition build() {
        return new CameraPosition(this.ajq, this.ajr, this.ajs, this.ajt);
    }
    
    public CameraPosition$Builder target(final LatLng ajq) {
        this.ajq = ajq;
        return this;
    }
    
    public CameraPosition$Builder tilt(final float ajs) {
        this.ajs = ajs;
        return this;
    }
    
    public CameraPosition$Builder zoom(final float ajr) {
        this.ajr = ajr;
        return this;
    }
}
