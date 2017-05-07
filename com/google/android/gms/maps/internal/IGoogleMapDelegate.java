// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.location.Location;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.internal.e;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.CircleOptions;
import android.os.IInterface;

public interface IGoogleMapDelegate extends IInterface
{
    b addCircle(final CircleOptions p0) throws RemoteException;
    
    c addGroundOverlay(final GroundOverlayOptions p0) throws RemoteException;
    
    d addMarker(final MarkerOptions p0) throws RemoteException;
    
    e addPolygon(final PolygonOptions p0) throws RemoteException;
    
    IPolylineDelegate addPolyline(final PolylineOptions p0) throws RemoteException;
    
    f addTileOverlay(final TileOverlayOptions p0) throws RemoteException;
    
    void animateCamera(final com.google.android.gms.dynamic.b p0) throws RemoteException;
    
    void animateCameraWithCallback(final com.google.android.gms.dynamic.b p0, final com.google.android.gms.maps.internal.b p1) throws RemoteException;
    
    void animateCameraWithDurationAndCallback(final com.google.android.gms.dynamic.b p0, final int p1, final com.google.android.gms.maps.internal.b p2) throws RemoteException;
    
    void clear() throws RemoteException;
    
    CameraPosition getCameraPosition() throws RemoteException;
    
    int getMapType() throws RemoteException;
    
    float getMaxZoomLevel() throws RemoteException;
    
    float getMinZoomLevel() throws RemoteException;
    
    Location getMyLocation() throws RemoteException;
    
    IProjectionDelegate getProjection() throws RemoteException;
    
    com.google.android.gms.dynamic.b getTestingHelper() throws RemoteException;
    
    IUiSettingsDelegate getUiSettings() throws RemoteException;
    
    boolean isBuildingsEnabled() throws RemoteException;
    
    boolean isIndoorEnabled() throws RemoteException;
    
    boolean isMyLocationEnabled() throws RemoteException;
    
    boolean isTrafficEnabled() throws RemoteException;
    
    void moveCamera(final com.google.android.gms.dynamic.b p0) throws RemoteException;
    
    void setBuildingsEnabled(final boolean p0) throws RemoteException;
    
    boolean setIndoorEnabled(final boolean p0) throws RemoteException;
    
    void setInfoWindowAdapter(final com.google.android.gms.maps.internal.d p0) throws RemoteException;
    
    void setLocationSource(final ILocationSourceDelegate p0) throws RemoteException;
    
    void setMapType(final int p0) throws RemoteException;
    
    void setMyLocationEnabled(final boolean p0) throws RemoteException;
    
    void setOnCameraChangeListener(final com.google.android.gms.maps.internal.e p0) throws RemoteException;
    
    void setOnInfoWindowClickListener(final com.google.android.gms.maps.internal.f p0) throws RemoteException;
    
    void setOnMapClickListener(final h p0) throws RemoteException;
    
    void setOnMapLoadedCallback(final i p0) throws RemoteException;
    
    void setOnMapLongClickListener(final j p0) throws RemoteException;
    
    void setOnMarkerClickListener(final k p0) throws RemoteException;
    
    void setOnMarkerDragListener(final l p0) throws RemoteException;
    
    void setOnMyLocationButtonClickListener(final m p0) throws RemoteException;
    
    void setOnMyLocationChangeListener(final n p0) throws RemoteException;
    
    void setPadding(final int p0, final int p1, final int p2, final int p3) throws RemoteException;
    
    void setTrafficEnabled(final boolean p0) throws RemoteException;
    
    void snapshot(final o p0, final com.google.android.gms.dynamic.b p1) throws RemoteException;
    
    void stopAnimation() throws RemoteException;
    
    public abstract static class a extends Binder implements IGoogleMapDelegate
    {
        public static IGoogleMapDelegate R(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleMapDelegate) {
                return (IGoogleMapDelegate)queryLocalInterface;
            }
            return new IGoogleMapDelegate.a.a(binder);
        }
        
        public boolean onTransact(int mapType, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            boolean trafficEnabled = false;
            final int n2 = 0;
            final int n3 = 0;
            final int n4 = 0;
            final boolean b = false;
            final int n5 = 0;
            final boolean b2 = false;
            final int n6 = 0;
            final IBinder binder = null;
            final IBinder binder2 = null;
            final IBinder binder3 = null;
            final IBinder binder4 = null;
            final IBinder binder5 = null;
            final IBinder binder6 = null;
            final IBinder binder7 = null;
            final IBinder binder8 = null;
            final IBinder binder9 = null;
            switch (mapType) {
                default: {
                    return super.onTransact(mapType, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final CameraPosition cameraPosition = this.getCameraPosition();
                    parcel2.writeNoException();
                    if (cameraPosition != null) {
                        parcel2.writeInt(1);
                        cameraPosition.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final float maxZoomLevel = this.getMaxZoomLevel();
                    parcel2.writeNoException();
                    parcel2.writeFloat(maxZoomLevel);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final float minZoomLevel = this.getMinZoomLevel();
                    parcel2.writeNoException();
                    parcel2.writeFloat(minZoomLevel);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.moveCamera(com.google.android.gms.dynamic.b.a.E(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.animateCamera(com.google.android.gms.dynamic.b.a.E(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.animateCameraWithCallback(com.google.android.gms.dynamic.b.a.E(parcel.readStrongBinder()), com.google.android.gms.maps.internal.b.a.P(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.animateCameraWithDurationAndCallback(com.google.android.gms.dynamic.b.a.E(parcel.readStrongBinder()), parcel.readInt(), com.google.android.gms.maps.internal.b.a.P(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.stopAnimation();
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    PolylineOptions fromParcel;
                    if (parcel.readInt() != 0) {
                        fromParcel = PolylineOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel = null;
                    }
                    final IPolylineDelegate addPolyline = this.addPolyline(fromParcel);
                    parcel2.writeNoException();
                    IBinder binder10 = binder9;
                    if (addPolyline != null) {
                        binder10 = addPolyline.asBinder();
                    }
                    parcel2.writeStrongBinder(binder10);
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    PolygonOptions fromParcel2;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = PolygonOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel2 = null;
                    }
                    final e addPolygon = this.addPolygon(fromParcel2);
                    parcel2.writeNoException();
                    IBinder binder11 = binder;
                    if (addPolygon != null) {
                        binder11 = addPolygon.asBinder();
                    }
                    parcel2.writeStrongBinder(binder11);
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    MarkerOptions fromParcel3;
                    if (parcel.readInt() != 0) {
                        fromParcel3 = MarkerOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel3 = null;
                    }
                    final d addMarker = this.addMarker(fromParcel3);
                    parcel2.writeNoException();
                    IBinder binder12 = binder2;
                    if (addMarker != null) {
                        binder12 = addMarker.asBinder();
                    }
                    parcel2.writeStrongBinder(binder12);
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    GroundOverlayOptions fromParcel4;
                    if (parcel.readInt() != 0) {
                        fromParcel4 = GroundOverlayOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel4 = null;
                    }
                    final c addGroundOverlay = this.addGroundOverlay(fromParcel4);
                    parcel2.writeNoException();
                    IBinder binder13 = binder3;
                    if (addGroundOverlay != null) {
                        binder13 = addGroundOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(binder13);
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    TileOverlayOptions fromParcel5;
                    if (parcel.readInt() != 0) {
                        fromParcel5 = TileOverlayOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel5 = null;
                    }
                    final f addTileOverlay = this.addTileOverlay(fromParcel5);
                    parcel2.writeNoException();
                    IBinder binder14 = binder4;
                    if (addTileOverlay != null) {
                        binder14 = addTileOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(binder14);
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.clear();
                    parcel2.writeNoException();
                    return true;
                }
                case 15: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    mapType = this.getMapType();
                    parcel2.writeNoException();
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setMapType(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final boolean trafficEnabled2 = this.isTrafficEnabled();
                    parcel2.writeNoException();
                    mapType = n6;
                    if (trafficEnabled2) {
                        mapType = 1;
                    }
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (parcel.readInt() != 0) {
                        trafficEnabled = true;
                    }
                    this.setTrafficEnabled(trafficEnabled);
                    parcel2.writeNoException();
                    return true;
                }
                case 19: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final boolean indoorEnabled = this.isIndoorEnabled();
                    parcel2.writeNoException();
                    mapType = n2;
                    if (indoorEnabled) {
                        mapType = 1;
                    }
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 20: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final boolean setIndoorEnabled = this.setIndoorEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    mapType = n3;
                    if (setIndoorEnabled) {
                        mapType = 1;
                    }
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 21: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final boolean myLocationEnabled = this.isMyLocationEnabled();
                    parcel2.writeNoException();
                    mapType = n4;
                    if (myLocationEnabled) {
                        mapType = 1;
                    }
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 22: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean myLocationEnabled2 = b;
                    if (parcel.readInt() != 0) {
                        myLocationEnabled2 = true;
                    }
                    this.setMyLocationEnabled(myLocationEnabled2);
                    parcel2.writeNoException();
                    return true;
                }
                case 23: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final Location myLocation = this.getMyLocation();
                    parcel2.writeNoException();
                    if (myLocation != null) {
                        parcel2.writeInt(1);
                        myLocation.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                }
                case 24: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setLocationSource(ILocationSourceDelegate.a.T(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 25: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final IUiSettingsDelegate uiSettings = this.getUiSettings();
                    parcel2.writeNoException();
                    IBinder binder15 = binder5;
                    if (uiSettings != null) {
                        binder15 = uiSettings.asBinder();
                    }
                    parcel2.writeStrongBinder(binder15);
                    return true;
                }
                case 26: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final IProjectionDelegate projection = this.getProjection();
                    parcel2.writeNoException();
                    IBinder binder16 = binder6;
                    if (projection != null) {
                        binder16 = projection.asBinder();
                    }
                    parcel2.writeStrongBinder(binder16);
                    return true;
                }
                case 27: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnCameraChangeListener(com.google.android.gms.maps.internal.e.a.W(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 28: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMapClickListener(h.a.Z(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 29: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMapLongClickListener(j.a.ab(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 30: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMarkerClickListener(k.a.ac(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 31: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMarkerDragListener(l.a.ad(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 32: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnInfoWindowClickListener(com.google.android.gms.maps.internal.f.a.X(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 33: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setInfoWindowAdapter(com.google.android.gms.maps.internal.d.a.S(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 34: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final com.google.android.gms.dynamic.b testingHelper = this.getTestingHelper();
                    parcel2.writeNoException();
                    IBinder binder17 = binder7;
                    if (testingHelper != null) {
                        binder17 = testingHelper.asBinder();
                    }
                    parcel2.writeStrongBinder(binder17);
                    return true;
                }
                case 35: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    CircleOptions fromParcel6;
                    if (parcel.readInt() != 0) {
                        fromParcel6 = CircleOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel6 = null;
                    }
                    final b addCircle = this.addCircle(fromParcel6);
                    parcel2.writeNoException();
                    IBinder binder18 = binder8;
                    if (addCircle != null) {
                        binder18 = addCircle.asBinder();
                    }
                    parcel2.writeStrongBinder(binder18);
                    return true;
                }
                case 36: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMyLocationChangeListener(n.a.af(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 37: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMyLocationButtonClickListener(m.a.ae(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 38: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.snapshot(o.a.ah(parcel.readStrongBinder()), com.google.android.gms.dynamic.b.a.E(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 39: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setPadding(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 40: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    final boolean buildingsEnabled = this.isBuildingsEnabled();
                    parcel2.writeNoException();
                    mapType = n5;
                    if (buildingsEnabled) {
                        mapType = 1;
                    }
                    parcel2.writeInt(mapType);
                    return true;
                }
                case 41: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean buildingsEnabled2 = b2;
                    if (parcel.readInt() != 0) {
                        buildingsEnabled2 = true;
                    }
                    this.setBuildingsEnabled(buildingsEnabled2);
                    parcel2.writeNoException();
                    return true;
                }
                case 42: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.setOnMapLoadedCallback(i.a.aa(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements IGoogleMapDelegate
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public b addCircle(final CircleOptions circleOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (circleOptions != null) {
                        obtain.writeInt(1);
                        circleOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return b.a.ak(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public c addGroundOverlay(final GroundOverlayOptions groundOverlayOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        obtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return c.a.al(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d addMarker(final MarkerOptions markerOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        obtain.writeInt(1);
                        markerOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public e addPolygon(final PolygonOptions polygonOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polygonOptions != null) {
                        obtain.writeInt(1);
                        polygonOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return e.a.an(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IPolylineDelegate addPolyline(final PolylineOptions polylineOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polylineOptions != null) {
                        obtain.writeInt(1);
                        polylineOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return IPolylineDelegate.a.ao(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public f addTileOverlay(final TileOverlayOptions tileOverlayOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (tileOverlayOptions != null) {
                        obtain.writeInt(1);
                        tileOverlayOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return f.a.ap(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void animateCamera(final com.google.android.gms.dynamic.b b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (b != null) {
                        binder = b.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void animateCameraWithCallback(final com.google.android.gms.dynamic.b b, final com.google.android.gms.maps.internal.b b2) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder2;
                    if (b != null) {
                        binder2 = b.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    IBinder binder3 = binder;
                    if (b2 != null) {
                        binder3 = b2.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.dU.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void animateCameraWithDurationAndCallback(final com.google.android.gms.dynamic.b b, final int n, final com.google.android.gms.maps.internal.b b2) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder2;
                    if (b != null) {
                        binder2 = b.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    obtain.writeInt(n);
                    IBinder binder3 = binder;
                    if (b2 != null) {
                        binder3 = b2.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.dU.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
            
            @Override
            public void clear() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public CameraPosition getCameraPosition() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    CameraPosition fromParcel;
                    if (obtain2.readInt() != 0) {
                        fromParcel = CameraPosition.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        fromParcel = null;
                    }
                    return fromParcel;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getMapType() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getMaxZoomLevel() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public float getMinZoomLevel() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public Location getMyLocation() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    Location location;
                    if (obtain2.readInt() != 0) {
                        location = (Location)Location.CREATOR.createFromParcel(obtain2);
                    }
                    else {
                        location = null;
                    }
                    return location;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IProjectionDelegate getProjection() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return IProjectionDelegate.a.ag(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public com.google.android.gms.dynamic.b getTestingHelper() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.b.a.E(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IUiSettingsDelegate getUiSettings() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return IUiSettingsDelegate.a.ai(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isBuildingsEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isIndoorEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isMyLocationEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isTrafficEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        b = true;
                    }
                    return b;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void moveCamera(final com.google.android.gms.dynamic.b b) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (b != null) {
                        binder = b.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setBuildingsEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean setIndoorEnabled(final boolean b) throws RemoteException {
                final boolean b2 = true;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    int n;
                    if (b) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 && b2;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setInfoWindowAdapter(final com.google.android.gms.maps.internal.d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setLocationSource(final ILocationSourceDelegate locationSourceDelegate) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (locationSourceDelegate != null) {
                        binder = locationSourceDelegate.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMapType(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(n);
                    this.dU.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setMyLocationEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnCameraChangeListener(final com.google.android.gms.maps.internal.e e) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (e != null) {
                        binder = e.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnInfoWindowClickListener(final com.google.android.gms.maps.internal.f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMapClickListener(final h h) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (h != null) {
                        binder = h.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMapLoadedCallback(final i i) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (i != null) {
                        binder = i.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMapLongClickListener(final j j) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (j != null) {
                        binder = j.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMarkerClickListener(final k k) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (k != null) {
                        binder = k.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMarkerDragListener(final l l) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (l != null) {
                        binder = l.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMyLocationButtonClickListener(final m m) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (m != null) {
                        binder = m.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnMyLocationChangeListener(final n n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder;
                    if (n != null) {
                        binder = n.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPadding(final int n, final int n2, final int n3, final int n4) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    obtain.writeInt(n4);
                    this.dU.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setTrafficEnabled(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.dU.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void snapshot(final o o, final com.google.android.gms.dynamic.b b) throws RemoteException {
                final IBinder binder = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder binder2;
                    if (o != null) {
                        binder2 = o.asBinder();
                    }
                    else {
                        binder2 = null;
                    }
                    obtain.writeStrongBinder(binder2);
                    IBinder binder3 = binder;
                    if (b != null) {
                        binder3 = b.asBinder();
                    }
                    obtain.writeStrongBinder(binder3);
                    this.dU.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void stopAnimation() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.dU.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
