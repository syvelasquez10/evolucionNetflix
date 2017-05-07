// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import android.view.View;
import com.google.android.gms.maps.internal.o;
import android.graphics.Bitmap;
import com.google.android.gms.maps.internal.n;
import com.google.android.gms.maps.internal.m;
import com.google.android.gms.maps.internal.l;
import com.google.android.gms.maps.internal.k;
import com.google.android.gms.maps.internal.j;
import com.google.android.gms.maps.internal.i;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.internal.h;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.internal.g;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import android.location.Location;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import android.os.RemoteException;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.internal.eg;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;

public final class GoogleMap
{
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate Br;
    private UiSettings Bs;
    
    protected GoogleMap(final IGoogleMapDelegate googleMapDelegate) {
        this.Br = eg.f(googleMapDelegate);
    }
    
    public final Circle addCircle(final CircleOptions circleOptions) {
        try {
            return new Circle(this.Br.addCircle(circleOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final GroundOverlay addGroundOverlay(final GroundOverlayOptions groundOverlayOptions) {
        try {
            final c addGroundOverlay = this.Br.addGroundOverlay(groundOverlayOptions);
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
            final d addMarker = this.Br.addMarker(markerOptions);
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
            return new Polygon(this.Br.addPolygon(polygonOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Polyline addPolyline(final PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.Br.addPolyline(polylineOptions));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final TileOverlay addTileOverlay(final TileOverlayOptions tileOverlayOptions) {
        try {
            final f addTileOverlay = this.Br.addTileOverlay(tileOverlayOptions);
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
            this.Br.animateCamera(cameraUpdate.el());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final int n, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate br = this.Br;
            final b el = cameraUpdate.el();
            com.google.android.gms.maps.internal.b b;
            if (cancelableCallback == null) {
                b = null;
            }
            else {
                b = new a(cancelableCallback);
            }
            br.animateCameraWithDurationAndCallback(el, n, b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void animateCamera(final CameraUpdate cameraUpdate, final CancelableCallback cancelableCallback) {
        try {
            final IGoogleMapDelegate br = this.Br;
            final b el = cameraUpdate.el();
            com.google.android.gms.maps.internal.b b;
            if (cancelableCallback == null) {
                b = null;
            }
            else {
                b = new a(cancelableCallback);
            }
            br.animateCameraWithCallback(el, b);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void clear() {
        try {
            this.Br.clear();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    IGoogleMapDelegate en() {
        return this.Br;
    }
    
    public final CameraPosition getCameraPosition() {
        try {
            return this.Br.getCameraPosition();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final int getMapType() {
        try {
            return this.Br.getMapType();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMaxZoomLevel() {
        try {
            return this.Br.getMaxZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final float getMinZoomLevel() {
        try {
            return this.Br.getMinZoomLevel();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.Br.getMyLocation();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final Projection getProjection() {
        try {
            return new Projection(this.Br.getProjection());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final UiSettings getUiSettings() {
        try {
            if (this.Bs == null) {
                this.Bs = new UiSettings(this.Br.getUiSettings());
            }
            return this.Bs;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isBuildingsEnabled() {
        try {
            return this.Br.isBuildingsEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isIndoorEnabled() {
        try {
            return this.Br.isIndoorEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isMyLocationEnabled() {
        try {
            return this.Br.isMyLocationEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean isTrafficEnabled() {
        try {
            return this.Br.isTrafficEnabled();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void moveCamera(final CameraUpdate cameraUpdate) {
        try {
            this.Br.moveCamera(cameraUpdate.el());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setBuildingsEnabled(final boolean buildingsEnabled) {
        try {
            this.Br.setBuildingsEnabled(buildingsEnabled);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final boolean setIndoorEnabled(final boolean indoorEnabled) {
        try {
            return this.Br.setIndoorEnabled(indoorEnabled);
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
                this.Br.setInfoWindowAdapter(null);
                return;
                this.Br.setInfoWindowAdapter(new com.google.android.gms.maps.internal.d.a() {
                    public b f(final com.google.android.gms.maps.model.internal.d d) {
                        return com.google.android.gms.dynamic.c.h(infoWindowAdapter.getInfoWindow(new Marker(d)));
                    }
                    
                    public b g(final com.google.android.gms.maps.model.internal.d d) {
                        return com.google.android.gms.dynamic.c.h(infoWindowAdapter.getInfoContents(new Marker(d)));
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
                this.Br.setLocationSource(null);
                return;
                this.Br.setLocationSource(new ILocationSourceDelegate.a() {
                    public void activate(final g g) {
                        locationSource.activate((LocationSource.OnLocationChangedListener)new LocationSource.OnLocationChangedListener() {
                            @Override
                            public void onLocationChanged(final Location location) {
                                try {
                                    g.g(com.google.android.gms.dynamic.c.h(location));
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
            this.Br.setMapType(mapType);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setMyLocationEnabled(final boolean myLocationEnabled) {
        try {
            this.Br.setMyLocationEnabled(myLocationEnabled);
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
                this.Br.setOnCameraChangeListener(null);
                return;
                this.Br.setOnCameraChangeListener(new e.a() {
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
    
    public final void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        Label_0015: {
            if (onInfoWindowClickListener != null) {
                break Label_0015;
            }
            try {
                this.Br.setOnInfoWindowClickListener(null);
                return;
                this.Br.setOnInfoWindowClickListener(new com.google.android.gms.maps.internal.f.a() {
                    public void e(final d d) {
                        onInfoWindowClickListener.onInfoWindowClick(new Marker(d));
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
                this.Br.setOnMapClickListener(null);
                return;
                this.Br.setOnMapClickListener(new h.a() {
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
                this.Br.setOnMapLoadedCallback(null);
                return;
                this.Br.setOnMapLoadedCallback(new i.a() {
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
                this.Br.setOnMapLongClickListener(null);
                return;
                this.Br.setOnMapLongClickListener(new j.a() {
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
                this.Br.setOnMarkerClickListener(null);
                return;
                this.Br.setOnMarkerClickListener(new k.a() {
                    public boolean a(final d d) {
                        return onMarkerClickListener.onMarkerClick(new Marker(d));
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
                this.Br.setOnMarkerDragListener(null);
                return;
                this.Br.setOnMarkerDragListener(new l.a() {
                    public void b(final d d) {
                        onMarkerDragListener.onMarkerDragStart(new Marker(d));
                    }
                    
                    public void c(final d d) {
                        onMarkerDragListener.onMarkerDragEnd(new Marker(d));
                    }
                    
                    public void d(final d d) {
                        onMarkerDragListener.onMarkerDrag(new Marker(d));
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
                this.Br.setOnMyLocationButtonClickListener(null);
                return;
                this.Br.setOnMyLocationButtonClickListener(new m.a() {
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
                this.Br.setOnMyLocationChangeListener(null);
                return;
                this.Br.setOnMyLocationChangeListener(new n.a() {
                    public void d(final b b) {
                        onMyLocationChangeListener.onMyLocationChange(com.google.android.gms.dynamic.c.b(b));
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
            this.Br.setPadding(n, n2, n3, n4);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public final void setTrafficEnabled(final boolean trafficEnabled) {
        try {
            this.Br.setTrafficEnabled(trafficEnabled);
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
            b h = com.google.android.gms.dynamic.c.h(bitmap);
            while (true) {
                final com.google.android.gms.dynamic.c c = (com.google.android.gms.dynamic.c)h;
                try {
                    this.Br.snapshot(new o.a() {
                        public void c(final b b) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(com.google.android.gms.dynamic.c.b(b));
                        }
                        
                        public void onSnapshotReady(final Bitmap bitmap) throws RemoteException {
                            snapshotReadyCallback.onSnapshotReady(bitmap);
                        }
                    }, c);
                    return;
                    h = null;
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        }
    }
    
    public final void stopAnimation() {
        try {
            this.Br.stopAnimation();
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
        private final CancelableCallback BI;
        
        a(final CancelableCallback bi) {
            this.BI = bi;
        }
        
        public void onCancel() {
            this.BI.onCancel();
        }
        
        public void onFinish() {
            this.BI.onFinish();
        }
    }
}
