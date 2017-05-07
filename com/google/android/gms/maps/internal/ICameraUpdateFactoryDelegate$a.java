// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.CameraPosition;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ICameraUpdateFactoryDelegate$a extends Binder implements ICameraUpdateFactoryDelegate
{
    public static ICameraUpdateFactoryDelegate aN(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof ICameraUpdateFactoryDelegate) {
            return (ICameraUpdateFactoryDelegate)queryLocalInterface;
        }
        return new ICameraUpdateFactoryDelegate$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        final IBinder binder3 = null;
        final IBinder binder4 = null;
        final IBinder binder5 = null;
        final IBinder binder6 = null;
        final IBinder binder7 = null;
        final IBinder binder8 = null;
        final IBinder binder9 = null;
        final IBinder binder10 = null;
        final IBinder binder11 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d zoomIn = this.zoomIn();
                parcel2.writeNoException();
                IBinder binder12 = binder11;
                if (zoomIn != null) {
                    binder12 = zoomIn.asBinder();
                }
                parcel2.writeStrongBinder(binder12);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d zoomOut = this.zoomOut();
                parcel2.writeNoException();
                IBinder binder13 = binder;
                if (zoomOut != null) {
                    binder13 = zoomOut.asBinder();
                }
                parcel2.writeStrongBinder(binder13);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d scrollBy = this.scrollBy(parcel.readFloat(), parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder14 = binder2;
                if (scrollBy != null) {
                    binder14 = scrollBy.asBinder();
                }
                parcel2.writeStrongBinder(binder14);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d zoomTo = this.zoomTo(parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder15 = binder3;
                if (zoomTo != null) {
                    binder15 = zoomTo.asBinder();
                }
                parcel2.writeStrongBinder(binder15);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d zoomBy = this.zoomBy(parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder16 = binder4;
                if (zoomBy != null) {
                    binder16 = zoomBy.asBinder();
                }
                parcel2.writeStrongBinder(binder16);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                final d zoomByWithFocus = this.zoomByWithFocus(parcel.readFloat(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                IBinder binder17 = binder5;
                if (zoomByWithFocus != null) {
                    binder17 = zoomByWithFocus.asBinder();
                }
                parcel2.writeStrongBinder(binder17);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                CameraPosition ci;
                if (parcel.readInt() != 0) {
                    ci = CameraPosition.CREATOR.cI(parcel);
                }
                else {
                    ci = null;
                }
                final d cameraPosition = this.newCameraPosition(ci);
                parcel2.writeNoException();
                IBinder binder18 = binder6;
                if (cameraPosition != null) {
                    binder18 = cameraPosition.asBinder();
                }
                parcel2.writeStrongBinder(binder18);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLng cm;
                if (parcel.readInt() != 0) {
                    cm = LatLng.CREATOR.cM(parcel);
                }
                else {
                    cm = null;
                }
                final d latLng = this.newLatLng(cm);
                parcel2.writeNoException();
                IBinder binder19 = binder7;
                if (latLng != null) {
                    binder19 = latLng.asBinder();
                }
                parcel2.writeStrongBinder(binder19);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLng cm2;
                if (parcel.readInt() != 0) {
                    cm2 = LatLng.CREATOR.cM(parcel);
                }
                else {
                    cm2 = null;
                }
                final d latLngZoom = this.newLatLngZoom(cm2, parcel.readFloat());
                parcel2.writeNoException();
                IBinder binder20 = binder8;
                if (latLngZoom != null) {
                    binder20 = latLngZoom.asBinder();
                }
                parcel2.writeStrongBinder(binder20);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLngBounds cl;
                if (parcel.readInt() != 0) {
                    cl = LatLngBounds.CREATOR.cL(parcel);
                }
                else {
                    cl = null;
                }
                final d latLngBounds = this.newLatLngBounds(cl, parcel.readInt());
                parcel2.writeNoException();
                IBinder binder21 = binder9;
                if (latLngBounds != null) {
                    binder21 = latLngBounds.asBinder();
                }
                parcel2.writeStrongBinder(binder21);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                LatLngBounds cl2;
                if (parcel.readInt() != 0) {
                    cl2 = LatLngBounds.CREATOR.cL(parcel);
                }
                else {
                    cl2 = null;
                }
                final d latLngBoundsWithSize = this.newLatLngBoundsWithSize(cl2, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                IBinder binder22 = binder10;
                if (latLngBoundsWithSize != null) {
                    binder22 = latLngBoundsWithSize.asBinder();
                }
                parcel2.writeStrongBinder(binder22);
                return true;
            }
        }
    }
}
