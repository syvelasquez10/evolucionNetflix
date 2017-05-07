// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import android.os.IInterface;

public interface ICameraUpdateFactoryDelegate extends IInterface
{
    d newCameraPosition(final CameraPosition p0);
    
    d newLatLng(final LatLng p0);
    
    d newLatLngBounds(final LatLngBounds p0, final int p1);
    
    d newLatLngBoundsWithSize(final LatLngBounds p0, final int p1, final int p2, final int p3);
    
    d newLatLngZoom(final LatLng p0, final float p1);
    
    d scrollBy(final float p0, final float p1);
    
    d zoomBy(final float p0);
    
    d zoomByWithFocus(final float p0, final int p1, final int p2);
    
    d zoomIn();
    
    d zoomOut();
    
    d zoomTo(final float p0);
}
