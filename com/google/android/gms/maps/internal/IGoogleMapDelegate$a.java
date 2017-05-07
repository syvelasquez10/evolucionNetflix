// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.internal.b;
import android.location.Location;
import com.google.android.gms.maps.model.internal.h;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.dynamic.d$a;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IGoogleMapDelegate$a extends Binder implements IGoogleMapDelegate
{
    public static IGoogleMapDelegate aQ(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IGoogleMapDelegate) {
            return (IGoogleMapDelegate)queryLocalInterface;
        }
        return new IGoogleMapDelegate$a$a(binder);
    }
    
    public boolean onTransact(int mapType, final Parcel parcel, final Parcel parcel2, final int n) {
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
                this.moveCamera(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.animateCamera(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.animateCameraWithCallback(d$a.am(parcel.readStrongBinder()), b$a.aO(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.animateCameraWithDurationAndCallback(d$a.am(parcel.readStrongBinder()), parcel.readInt(), b$a.aO(parcel.readStrongBinder()));
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
                PolylineOptions cp;
                if (parcel.readInt() != 0) {
                    cp = PolylineOptions.CREATOR.cP(parcel);
                }
                else {
                    cp = null;
                }
                final IPolylineDelegate addPolyline = this.addPolyline(cp);
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
                PolygonOptions co;
                if (parcel.readInt() != 0) {
                    co = PolygonOptions.CREATOR.cO(parcel);
                }
                else {
                    co = null;
                }
                final g addPolygon = this.addPolygon(co);
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
                MarkerOptions cn;
                if (parcel.readInt() != 0) {
                    cn = MarkerOptions.CREATOR.cN(parcel);
                }
                else {
                    cn = null;
                }
                final f addMarker = this.addMarker(cn);
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
                GroundOverlayOptions ck;
                if (parcel.readInt() != 0) {
                    ck = GroundOverlayOptions.CREATOR.cK(parcel);
                }
                else {
                    ck = null;
                }
                final c addGroundOverlay = this.addGroundOverlay(ck);
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
                TileOverlayOptions cv;
                if (parcel.readInt() != 0) {
                    cv = TileOverlayOptions.CREATOR.cV(parcel);
                }
                else {
                    cv = null;
                }
                final h addTileOverlay = this.addTileOverlay(cv);
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
                this.setLocationSource(ILocationSourceDelegate$a.aS(parcel.readStrongBinder()));
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
                this.setOnCameraChangeListener(e$a.aV(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 28: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMapClickListener(i$a.aZ(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 29: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMapLongClickListener(k$a.bb(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 30: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMarkerClickListener(l$a.bc(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 31: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMarkerDragListener(m$a.bd(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 32: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnInfoWindowClickListener(g$a.aX(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 33: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setInfoWindowAdapter(com.google.android.gms.maps.internal.d$a.aR(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 35: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                CircleOptions cj;
                if (parcel.readInt() != 0) {
                    cj = CircleOptions.CREATOR.cJ(parcel);
                }
                else {
                    cj = null;
                }
                final b addCircle = this.addCircle(cj);
                parcel2.writeNoException();
                IBinder binder17 = binder7;
                if (addCircle != null) {
                    binder17 = addCircle.asBinder();
                }
                parcel2.writeStrongBinder(binder17);
                return true;
            }
            case 36: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMyLocationChangeListener(o$a.bf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 37: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnMyLocationButtonClickListener(n$a.be(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 38: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.snapshot(s$a.bk(parcel.readStrongBinder()), d$a.am(parcel.readStrongBinder()));
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
                this.setOnMapLoadedCallback(j$a.ba(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 44: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                final d focusedBuilding = this.getFocusedBuilding();
                parcel2.writeNoException();
                IBinder binder18 = binder8;
                if (focusedBuilding != null) {
                    binder18 = focusedBuilding.asBinder();
                }
                parcel2.writeStrongBinder(binder18);
                return true;
            }
            case 45: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                this.setOnIndoorStateChangeListener(f$a.aW(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
