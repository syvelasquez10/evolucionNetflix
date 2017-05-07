// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.s;
import android.graphics.Bitmap;
import com.google.android.gms.maps.internal.o;
import com.google.android.gms.maps.internal.m;
import com.google.android.gms.maps.internal.l;
import com.google.android.gms.maps.internal.k;
import com.google.android.gms.maps.internal.j;
import com.google.android.gms.maps.internal.i;
import com.google.android.gms.maps.internal.g;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import android.location.Location;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.internal.b;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.internal.h;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;

public final class GoogleMap
{
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate aic;
    private UiSettings aid;
    
    protected GoogleMap(final IGoogleMapDelegate googleMapDelegate) {
        this.aic = n.i(googleMapDelegate);
    }
    
    public final Circle addCircle(final CircleOptions circleOptions) {
        try {
            return new Circle(this.aic.addCircle(circleOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final GroundOverlay addGroundOverlay(final GroundOverlayOptions groundOverlayOptions) {
        try {
            final c addGroundOverlay = this.aic.addGroundOverlay(groundOverlayOptions);
            if (addGroundOverlay != null) {
                return new GroundOverlay(addGroundOverlay);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Marker addMarker(final MarkerOptions markerOptions) {
        try {
            final f addMarker = this.aic.addMarker(markerOptions);
            if (addMarker != null) {
                return new Marker(addMarker);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Polygon addPolygon(final PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.aic.addPolygon(polygonOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Polyline addPolyline(final PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.aic.addPolyline(polylineOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final TileOverlay addTileOverlay(final TileOverlayOptions tileOverlayOptions) {
        try {
            final h addTileOverlay = this.aic.addTileOverlay(tileOverlayOptions);
            if (addTileOverlay != null) {
                return new TileOverlay(addTileOverlay);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate) {
        try {
            this.aic.animateCamera(cameraUpdate.mm());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final int n, final GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        try {
            final IGoogleMapDelegate aic = this.aic;
            final d mm = cameraUpdate.mm();
            b b;
            if (googleMap$CancelableCallback == null) {
                b = null;
            }
            else {
                b = new GoogleMap$a(googleMap$CancelableCallback);
            }
            aic.animateCameraWithDurationAndCallback(mm, n, b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        try {
            final IGoogleMapDelegate aic = this.aic;
            final d mm = cameraUpdate.mm();
            b b;
            if (googleMap$CancelableCallback == null) {
                b = null;
            }
            else {
                b = new GoogleMap$a(googleMap$CancelableCallback);
            }
            aic.animateCameraWithCallback(mm, b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void clear() {
        try {
            this.aic.clear();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final CameraPosition getCameraPosition() {
        try {
            return this.aic.getCameraPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public IndoorBuilding getFocusedBuilding() {
        try {
            final com.google.android.gms.maps.model.internal.d focusedBuilding = this.aic.getFocusedBuilding();
            if (focusedBuilding != null) {
                return new IndoorBuilding(focusedBuilding);
            }
            return null;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final int getMapType() {
        try {
            return this.aic.getMapType();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMaxZoomLevel() {
        try {
            return this.aic.getMaxZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMinZoomLevel() {
        try {
            return this.aic.getMinZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.aic.getMyLocation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Projection getProjection() {
        try {
            return new Projection(this.aic.getProjection());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final UiSettings getUiSettings() {
        try {
            if (this.aid == null) {
                this.aid = new UiSettings(this.aic.getUiSettings());
            }
            return this.aid;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isBuildingsEnabled() {
        try {
            return this.aic.isBuildingsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isIndoorEnabled() {
        try {
            return this.aic.isIndoorEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isMyLocationEnabled() {
        try {
            return this.aic.isMyLocationEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isTrafficEnabled() {
        try {
            return this.aic.isTrafficEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    IGoogleMapDelegate mo() {
        return this.aic;
    }
    
    public final void moveCamera(final CameraUpdate cameraUpdate) {
        try {
            this.aic.moveCamera(cameraUpdate.mm());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setBuildingsEnabled(final boolean buildingsEnabled) {
        try {
            this.aic.setBuildingsEnabled(buildingsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean setIndoorEnabled(final boolean indoorEnabled) {
        try {
            return this.aic.setIndoorEnabled(indoorEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setInfoWindowAdapter(final GoogleMap$InfoWindowAdapter googleMap$InfoWindowAdapter) {
        Label_0015: {
            if (googleMap$InfoWindowAdapter != null) {
                break Label_0015;
            }
            try {
                this.aic.setInfoWindowAdapter(null);
                return;
                this.aic.setInfoWindowAdapter(new GoogleMap$13(this, googleMap$InfoWindowAdapter));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setLocationSource(final LocationSource locationSource) {
        Label_0015: {
            if (locationSource != null) {
                break Label_0015;
            }
            try {
                this.aic.setLocationSource(null);
                return;
                this.aic.setLocationSource(new GoogleMap$6(this, locationSource));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setMapType(final int mapType) {
        try {
            this.aic.setMapType(mapType);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setMyLocationEnabled(final boolean myLocationEnabled) {
        try {
            this.aic.setMyLocationEnabled(myLocationEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setOnCameraChangeListener(final GoogleMap$OnCameraChangeListener googleMap$OnCameraChangeListener) {
        Label_0015: {
            if (googleMap$OnCameraChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnCameraChangeListener(null);
                return;
                this.aic.setOnCameraChangeListener(new GoogleMap$7(this, googleMap$OnCameraChangeListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnIndoorStateChangeListener(final GoogleMap$OnIndoorStateChangeListener googleMap$OnIndoorStateChangeListener) {
        Label_0015: {
            if (googleMap$OnIndoorStateChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnIndoorStateChangeListener(null);
                return;
                this.aic.setOnIndoorStateChangeListener(new GoogleMap$1(this, googleMap$OnIndoorStateChangeListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnInfoWindowClickListener(final GoogleMap$OnInfoWindowClickListener googleMap$OnInfoWindowClickListener) {
        Label_0015: {
            if (googleMap$OnInfoWindowClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnInfoWindowClickListener(null);
                return;
                this.aic.setOnInfoWindowClickListener(new GoogleMap$12(this, googleMap$OnInfoWindowClickListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapClickListener(final GoogleMap$OnMapClickListener googleMap$OnMapClickListener) {
        Label_0015: {
            if (googleMap$OnMapClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapClickListener(null);
                return;
                this.aic.setOnMapClickListener(new GoogleMap$8(this, googleMap$OnMapClickListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public void setOnMapLoadedCallback(final GoogleMap$OnMapLoadedCallback googleMap$OnMapLoadedCallback) {
        Label_0015: {
            if (googleMap$OnMapLoadedCallback != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapLoadedCallback(null);
                return;
                this.aic.setOnMapLoadedCallback(new GoogleMap$4(this, googleMap$OnMapLoadedCallback));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapLongClickListener(final GoogleMap$OnMapLongClickListener googleMap$OnMapLongClickListener) {
        Label_0015: {
            if (googleMap$OnMapLongClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapLongClickListener(null);
                return;
                this.aic.setOnMapLongClickListener(new GoogleMap$9(this, googleMap$OnMapLongClickListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerClickListener(final GoogleMap$OnMarkerClickListener googleMap$OnMarkerClickListener) {
        Label_0015: {
            if (googleMap$OnMarkerClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMarkerClickListener(null);
                return;
                this.aic.setOnMarkerClickListener(new GoogleMap$10(this, googleMap$OnMarkerClickListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerDragListener(final GoogleMap$OnMarkerDragListener googleMap$OnMarkerDragListener) {
        Label_0015: {
            if (googleMap$OnMarkerDragListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMarkerDragListener(null);
                return;
                this.aic.setOnMarkerDragListener(new GoogleMap$11(this, googleMap$OnMarkerDragListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMyLocationButtonClickListener(final GoogleMap$OnMyLocationButtonClickListener googleMap$OnMyLocationButtonClickListener) {
        Label_0015: {
            if (googleMap$OnMyLocationButtonClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMyLocationButtonClickListener(null);
                return;
                this.aic.setOnMyLocationButtonClickListener(new GoogleMap$3(this, googleMap$OnMyLocationButtonClickListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Deprecated
    public final void setOnMyLocationChangeListener(final GoogleMap$OnMyLocationChangeListener googleMap$OnMyLocationChangeListener) {
        Label_0015: {
            if (googleMap$OnMyLocationChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMyLocationChangeListener(null);
                return;
                this.aic.setOnMyLocationChangeListener(new GoogleMap$2(this, googleMap$OnMyLocationChangeListener));
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setPadding(final int n, final int n2, final int n3, final int n4) {
        try {
            this.aic.setPadding(n, n2, n3, n4);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setTrafficEnabled(final boolean trafficEnabled) {
        try {
            this.aic.setTrafficEnabled(trafficEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void snapshot(final GoogleMap$SnapshotReadyCallback googleMap$SnapshotReadyCallback) {
        this.snapshot(googleMap$SnapshotReadyCallback, null);
    }
    
    public final void snapshot(final GoogleMap$SnapshotReadyCallback googleMap$SnapshotReadyCallback, final Bitmap bitmap) {
        Label_0037: {
            if (bitmap == null) {
                break Label_0037;
            }
            d k = com.google.android.gms.dynamic.e.k(bitmap);
            while (true) {
                final com.google.android.gms.dynamic.e e = (com.google.android.gms.dynamic.e)k;
                try {
                    this.aic.snapshot(new GoogleMap$5(this, googleMap$SnapshotReadyCallback), e);
                    return;
                    k = null;
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
    }
    
    public final void stopAnimation() {
        try {
            this.aic.stopAnimation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
