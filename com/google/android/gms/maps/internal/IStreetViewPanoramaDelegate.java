// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.IInterface;

public interface IStreetViewPanoramaDelegate extends IInterface
{
    void animateTo(final StreetViewPanoramaCamera p0, final long p1);
    
    void enablePanning(final boolean p0);
    
    void enableStreetNames(final boolean p0);
    
    void enableUserNavigation(final boolean p0);
    
    void enableZoom(final boolean p0);
    
    StreetViewPanoramaCamera getPanoramaCamera();
    
    StreetViewPanoramaLocation getStreetViewPanoramaLocation();
    
    boolean isPanningGesturesEnabled();
    
    boolean isStreetNamesEnabled();
    
    boolean isUserNavigationEnabled();
    
    boolean isZoomGesturesEnabled();
    
    d orientationToPoint(final StreetViewPanoramaOrientation p0);
    
    StreetViewPanoramaOrientation pointToOrientation(final d p0);
    
    void setOnStreetViewPanoramaCameraChangeListener(final p p0);
    
    void setOnStreetViewPanoramaChangeListener(final q p0);
    
    void setOnStreetViewPanoramaClickListener(final r p0);
    
    void setPosition(final LatLng p0);
    
    void setPositionWithID(final String p0);
    
    void setPositionWithRadius(final LatLng p0, final int p1);
}
