// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.location.Location;
import com.google.android.gms.maps.model.internal.d$a;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.internal.h$a;
import com.google.android.gms.maps.model.internal.h;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate$a;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.internal.g$a;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.internal.f$a;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.internal.c$a;
import com.google.android.gms.maps.model.internal.c;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.internal.b$a;
import android.os.Parcel;
import com.google.android.gms.maps.model.internal.b;
import com.google.android.gms.maps.model.CircleOptions;
import android.os.IBinder;

class IGoogleMapDelegate$a$a implements IGoogleMapDelegate
{
    private IBinder lb;
    
    IGoogleMapDelegate$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public b addCircle(final CircleOptions circleOptions) {
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
            this.lb.transact(35, obtain, obtain2, 0);
            obtain2.readException();
            return b$a.bq(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public c addGroundOverlay(final GroundOverlayOptions groundOverlayOptions) {
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
            this.lb.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            return c$a.br(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public f addMarker(final MarkerOptions markerOptions) {
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
            this.lb.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            return f$a.bu(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public g addPolygon(final PolygonOptions polygonOptions) {
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
            this.lb.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            return g$a.bv(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IPolylineDelegate addPolyline(final PolylineOptions polylineOptions) {
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
            this.lb.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            return IPolylineDelegate$a.bw(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public h addTileOverlay(final TileOverlayOptions tileOverlayOptions) {
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
            this.lb.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            return h$a.bx(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void animateCamera(final d d) {
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
            this.lb.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void animateCameraWithCallback(final d d, final com.google.android.gms.maps.internal.b b) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
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
            this.lb.transact(6, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void animateCameraWithDurationAndCallback(final d d, final int n, final com.google.android.gms.maps.internal.b b) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IBinder binder2;
            if (d != null) {
                binder2 = d.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeInt(n);
            IBinder binder3 = binder;
            if (b != null) {
                binder3 = b.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            this.lb.transact(7, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void clear() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(14, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public CameraPosition getCameraPosition() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            CameraPosition ci;
            if (obtain2.readInt() != 0) {
                ci = CameraPosition.CREATOR.cI(obtain2);
            }
            else {
                ci = null;
            }
            return ci;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public com.google.android.gms.maps.model.internal.d getFocusedBuilding() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(44, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.bs(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int getMapType() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(15, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public float getMaxZoomLevel() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readFloat();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public float getMinZoomLevel() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readFloat();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Location getMyLocation() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(23, obtain, obtain2, 0);
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
    public IProjectionDelegate getProjection() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(26, obtain, obtain2, 0);
            obtain2.readException();
            return IProjectionDelegate$a.bj(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public IUiSettingsDelegate getUiSettings() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(25, obtain, obtain2, 0);
            obtain2.readException();
            return IUiSettingsDelegate$a.bo(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean isBuildingsEnabled() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(40, obtain, obtain2, 0);
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
    public boolean isIndoorEnabled() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(19, obtain, obtain2, 0);
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
    public boolean isMyLocationEnabled() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(21, obtain, obtain2, 0);
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
    public boolean isTrafficEnabled() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(17, obtain, obtain2, 0);
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
    public void moveCamera(final d d) {
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
            this.lb.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setBuildingsEnabled(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(41, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean setIndoorEnabled(final boolean b) {
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
            this.lb.transact(20, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt() != 0 && b2;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setInfoWindowAdapter(final com.google.android.gms.maps.internal.d d) {
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
            this.lb.transact(33, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setLocationSource(final ILocationSourceDelegate locationSourceDelegate) {
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
            this.lb.transact(24, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setMapType(final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            obtain.writeInt(n);
            this.lb.transact(16, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setMyLocationEnabled(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(22, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnCameraChangeListener(final e e) {
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
            this.lb.transact(27, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnIndoorStateChangeListener(final com.google.android.gms.maps.internal.f f) {
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
            this.lb.transact(45, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnInfoWindowClickListener(final com.google.android.gms.maps.internal.g g) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IBinder binder;
            if (g != null) {
                binder = g.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(32, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMapClickListener(final i i) {
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
            this.lb.transact(28, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMapLoadedCallback(final j j) {
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
            this.lb.transact(42, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMapLongClickListener(final k k) {
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
            this.lb.transact(29, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMarkerClickListener(final l l) {
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
            this.lb.transact(30, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMarkerDragListener(final m m) {
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
            this.lb.transact(31, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMyLocationButtonClickListener(final n n) {
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
            this.lb.transact(37, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setOnMyLocationChangeListener(final o o) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IBinder binder;
            if (o != null) {
                binder = o.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(36, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setPadding(final int n, final int n2, final int n3, final int n4) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            obtain.writeInt(n4);
            this.lb.transact(39, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void setTrafficEnabled(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(18, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void snapshot(final s s, final d d) {
        final IBinder binder = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            IBinder binder2;
            if (s != null) {
                binder2 = s.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            IBinder binder3 = binder;
            if (d != null) {
                binder3 = d.asBinder();
            }
            obtain.writeStrongBinder(binder3);
            this.lb.transact(38, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void stopAnimation() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            this.lb.transact(8, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
