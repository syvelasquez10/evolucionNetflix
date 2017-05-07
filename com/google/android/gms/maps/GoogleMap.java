// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.view.View;
import com.google.android.gms.maps.internal.s;
import android.graphics.Bitmap;
import com.google.android.gms.maps.internal.o;
import com.google.android.gms.maps.internal.m;
import com.google.android.gms.maps.internal.l;
import com.google.android.gms.maps.internal.k;
import com.google.android.gms.maps.internal.j;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.internal.i;
import com.google.android.gms.maps.internal.g;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.dynamic.e;
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
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final int n, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate aic = this.aic;
            final d mm = cameraUpdate.mm();
            b b;
            if (cancelableCallback == null) {
                b = null;
            }
            else {
                b = new a(cancelableCallback);
            }
            aic.animateCameraWithDurationAndCallback(mm, n, b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate aic = this.aic;
            final d mm = cameraUpdate.mm();
            b b;
            if (cancelableCallback == null) {
                b = null;
            }
            else {
                b = new a(cancelableCallback);
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
    
    public final void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        Label_0015: {
            if (infoWindowAdapter != null) {
                break Label_0015;
            }
            try {
                this.aic.setInfoWindowAdapter(null);
                return;
                this.aic.setInfoWindowAdapter(new com.google.android.gms.maps.internal.d.a() {
                    public com.google.android.gms.dynamic.d f(final f f) {
                        return e.k(infoWindowAdapter.getInfoWindow(new Marker(f)));
                    }
                    
                    public com.google.android.gms.dynamic.d g(final f f) {
                        return e.k(infoWindowAdapter.getInfoContents(new Marker(f)));
                    }
                });
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
                this.aic.setLocationSource(new ILocationSourceDelegate.a() {
                    public void activate(final com.google.android.gms.maps.internal.h h) {
                        locationSource.activate((LocationSource.OnLocationChangedListener)new LocationSource.OnLocationChangedListener() {
                            @Override
                            public void onLocationChanged(final Location location) {
                                try {
                                    h.l(e.k(location));
                                }
                                catch (RemoteException ex) {
                                    throw new RuntimeRemoteException(ex);
                                }
                            }
                        });
                    }
                    
                    public void deactivate() {
                        locationSource.deactivate();
                    }
                });
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
    
    public final void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        Label_0015: {
            if (onCameraChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnCameraChangeListener(null);
                return;
                this.aic.setOnCameraChangeListener(new com.google.android.gms.maps.internal.e.a() {
                    public void onCameraChange(final CameraPosition cameraPosition) {
                        onCameraChangeListener.onCameraChange(cameraPosition);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnIndoorStateChangeListener(final OnIndoorStateChangeListener onIndoorStateChangeListener) {
        Label_0015: {
            if (onIndoorStateChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnIndoorStateChangeListener(null);
                return;
                this.aic.setOnIndoorStateChangeListener(new com.google.android.gms.maps.internal.f.a() {
                    public void a(final com.google.android.gms.maps.model.internal.d d) {
                        onIndoorStateChangeListener.onIndoorLevelActivated(new IndoorBuilding(d));
                    }
                    
                    public void onIndoorBuildingFocused() {
                        onIndoorStateChangeListener.onIndoorBuildingFocused();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        Label_0015: {
            if (onInfoWindowClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnInfoWindowClickListener(null);
                return;
                this.aic.setOnInfoWindowClickListener(new g.a() {
                    public void e(final f f) {
                        onInfoWindowClickListener.onInfoWindowClick(new Marker(f));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        Label_0015: {
            if (onMapClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapClickListener(null);
                return;
                this.aic.setOnMapClickListener(new i.a() {
                    public void onMapClick(final LatLng latLng) {
                        onMapClickListener.onMapClick(latLng);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public void setOnMapLoadedCallback(final OnMapLoadedCallback onMapLoadedCallback) {
        Label_0015: {
            if (onMapLoadedCallback != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapLoadedCallback(null);
                return;
                this.aic.setOnMapLoadedCallback(new j.a() {
                    public void onMapLoaded() throws RemoteException {
                        onMapLoadedCallback.onMapLoaded();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMapLongClickListener(final OnMapLongClickListener onMapLongClickListener) {
        Label_0015: {
            if (onMapLongClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMapLongClickListener(null);
                return;
                this.aic.setOnMapLongClickListener(new k.a() {
                    public void onMapLongClick(final LatLng latLng) {
                        onMapLongClickListener.onMapLongClick(latLng);
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        Label_0015: {
            if (onMarkerClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMarkerClickListener(null);
                return;
                this.aic.setOnMarkerClickListener(new l.a() {
                    public boolean a(final f f) {
                        return onMarkerClickListener.onMarkerClick(new Marker(f));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        Label_0015: {
            if (onMarkerDragListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMarkerDragListener(null);
                return;
                this.aic.setOnMarkerDragListener(new m.a() {
                    public void b(final f f) {
                        onMarkerDragListener.onMarkerDragStart(new Marker(f));
                    }
                    
                    public void c(final f f) {
                        onMarkerDragListener.onMarkerDragEnd(new Marker(f));
                    }
                    
                    public void d(final f f) {
                        onMarkerDragListener.onMarkerDrag(new Marker(f));
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    public final void setOnMyLocationButtonClickListener(final OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        Label_0015: {
            if (onMyLocationButtonClickListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMyLocationButtonClickListener(null);
                return;
                this.aic.setOnMyLocationButtonClickListener(new com.google.android.gms.maps.internal.n.a() {
                    public boolean onMyLocationButtonClick() throws RemoteException {
                        return onMyLocationButtonClickListener.onMyLocationButtonClick();
                    }
                });
            }
            catch (RemoteException ex) {
                throw new RuntimeRemoteException(ex);
            }
        }
    }
    
    @Deprecated
    public final void setOnMyLocationChangeListener(final OnMyLocationChangeListener onMyLocationChangeListener) {
        Label_0015: {
            if (onMyLocationChangeListener != null) {
                break Label_0015;
            }
            try {
                this.aic.setOnMyLocationChangeListener(null);
                return;
                this.aic.setOnMyLocationChangeListener(new o.a() {
                    public void g(final d d) {
                        onMyLocationChangeListener.onMyLocationChange(e.f(d));
                    }
                });
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
    
    public final void snapshot(final SnapshotReadyCallback snapshotReadyCallback) {
        this.snapshot(snapshotReadyCallback, null);
    }
    
    public final void snapshot(final SnapshotReadyCallback snapshotReadyCallback, final Bitmap bitmap) {
        Label_0037: {
            if (bitmap == null) {
                break Label_0037;
            }
            d k = e.k(bitmap);
            while (true) {
                final e e = (e)k;
                try {
                    this.aic.snapshot(new s.a() {
                        public void h(final d d) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(com.google.android.gms.dynamic.e.f(d));
                        }
                        
                        public void onSnapshotReady(final Bitmap bitmap) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(bitmap);
                        }
                    }, e);
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
    
    public interface CancelableCallback
    {
        void onCancel();
        
        void onFinish();
    }
    
    public interface InfoWindowAdapter
    {
        View getInfoContents(final Marker p0);
        
        View getInfoWindow(final Marker p0);
    }
    
    public interface OnCameraChangeListener
    {
        void onCameraChange(final CameraPosition p0);
    }
    
    public interface OnIndoorStateChangeListener
    {
        void onIndoorBuildingFocused();
        
        void onIndoorLevelActivated(final IndoorBuilding p0);
    }
    
    public interface OnInfoWindowClickListener
    {
        void onInfoWindowClick(final Marker p0);
    }
    
    public interface OnMapClickListener
    {
        void onMapClick(final LatLng p0);
    }
    
    public interface OnMapLoadedCallback
    {
        void onMapLoaded();
    }
    
    public interface OnMapLongClickListener
    {
        void onMapLongClick(final LatLng p0);
    }
    
    public interface OnMarkerClickListener
    {
        boolean onMarkerClick(final Marker p0);
    }
    
    public interface OnMarkerDragListener
    {
        void onMarkerDrag(final Marker p0);
        
        void onMarkerDragEnd(final Marker p0);
        
        void onMarkerDragStart(final Marker p0);
    }
    
    public interface OnMyLocationButtonClickListener
    {
        boolean onMyLocationButtonClick();
    }
    
    @Deprecated
    public interface OnMyLocationChangeListener
    {
        void onMyLocationChange(final Location p0);
    }
    
    public interface SnapshotReadyCallback
    {
        void onSnapshotReady(final Bitmap p0);
    }
    
    private static final class a extends b.a
    {
        private final CancelableCallback aiu;
        
        a(final CancelableCallback aiu) {
            this.aiu = aiu;
        }
        
        public void onCancel() {
            this.aiu.onCancel();
        }
        
        public void onFinish() {
            this.aiu.onFinish();
        }
    }
}
