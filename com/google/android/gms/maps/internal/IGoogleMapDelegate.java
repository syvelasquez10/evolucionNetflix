// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.location.Location;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.internal.h;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.CircleOptions;
import android.os.IInterface;

public interface IGoogleMapDelegate extends IInterface
{
    b addCircle(final CircleOptions p0);
    
    c addGroundOverlay(final GroundOverlayOptions p0);
    
    f addMarker(final MarkerOptions p0);
    
    g addPolygon(final PolygonOptions p0);
    
    IPolylineDelegate addPolyline(final PolylineOptions p0);
    
    h addTileOverlay(final TileOverlayOptions p0);
    
    void animateCamera(final d p0);
    
    void animateCameraWithCallback(final d p0, final com.google.android.gms.maps.internal.b p1);
    
    void animateCameraWithDurationAndCallback(final d p0, final int p1, final com.google.android.gms.maps.internal.b p2);
    
    void clear();
    
    CameraPosition getCameraPosition();
    
    com.google.android.gms.maps.model.internal.d getFocusedBuilding();
    
    int getMapType();
    
    float getMaxZoomLevel();
    
    float getMinZoomLevel();
    
    Location getMyLocation();
    
    IProjectionDelegate getProjection();
    
    IUiSettingsDelegate getUiSettings();
    
    boolean isBuildingsEnabled();
    
    boolean isIndoorEnabled();
    
    boolean isMyLocationEnabled();
    
    boolean isTrafficEnabled();
    
    void moveCamera(final d p0);
    
    void setBuildingsEnabled(final boolean p0);
    
    boolean setIndoorEnabled(final boolean p0);
    
    void setInfoWindowAdapter(final com.google.android.gms.maps.internal.d p0);
    
    void setLocationSource(final ILocationSourceDelegate p0);
    
    void setMapType(final int p0);
    
    void setMyLocationEnabled(final boolean p0);
    
    void setOnCameraChangeListener(final e p0);
    
    void setOnIndoorStateChangeListener(final com.google.android.gms.maps.internal.f p0);
    
    void setOnInfoWindowClickListener(final com.google.android.gms.maps.internal.g p0);
    
    void setOnMapClickListener(final i p0);
    
    void setOnMapLoadedCallback(final j p0);
    
    void setOnMapLongClickListener(final k p0);
    
    void setOnMarkerClickListener(final l p0);
    
    void setOnMarkerDragListener(final m p0);
    
    void setOnMyLocationButtonClickListener(final n p0);
    
    void setOnMyLocationChangeListener(final o p0);
    
    void setPadding(final int p0, final int p1, final int p2, final int p3);
    
    void setTrafficEnabled(final boolean p0);
    
    void snapshot(final s p0, final d p1);
    
    void stopAnimation();
}
