// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.dynamic.d$a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IStreetViewPanoramaDelegate$a extends Binder implements IStreetViewPanoramaDelegate
{
    public static IStreetViewPanoramaDelegate bl(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IStreetViewPanoramaDelegate) {
            return (IStreetViewPanoramaDelegate)queryLocalInterface;
        }
        return new IStreetViewPanoramaDelegate$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final LatLng latLng = null;
        final IBinder binder = null;
        final LatLng latLng2 = null;
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        boolean b4 = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                if (parcel.readInt() != 0) {
                    b4 = true;
                }
                this.enableZoom(b4);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean b5 = b;
                if (parcel.readInt() != 0) {
                    b5 = true;
                }
                this.enablePanning(b5);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean b6 = b2;
                if (parcel.readInt() != 0) {
                    b6 = true;
                }
                this.enableUserNavigation(b6);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                boolean b7 = b3;
                if (parcel.readInt() != 0) {
                    b7 = true;
                }
                this.enableStreetNames(b7);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final boolean zoomGesturesEnabled = this.isZoomGesturesEnabled();
                parcel2.writeNoException();
                n = n3;
                if (zoomGesturesEnabled) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final boolean panningGesturesEnabled = this.isPanningGesturesEnabled();
                parcel2.writeNoException();
                n = n4;
                if (panningGesturesEnabled) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final boolean userNavigationEnabled = this.isUserNavigationEnabled();
                parcel2.writeNoException();
                n = n5;
                if (userNavigationEnabled) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final boolean streetNamesEnabled = this.isStreetNamesEnabled();
                parcel2.writeNoException();
                n = n6;
                if (streetNamesEnabled) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                StreetViewPanoramaCamera cq;
                if (parcel.readInt() != 0) {
                    cq = StreetViewPanoramaCamera.CREATOR.cQ(parcel);
                }
                else {
                    cq = null;
                }
                this.animateTo(cq, parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final StreetViewPanoramaCamera panoramaCamera = this.getPanoramaCamera();
                parcel2.writeNoException();
                if (panoramaCamera != null) {
                    parcel2.writeInt(1);
                    panoramaCamera.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                this.setPositionWithID(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                LatLng cm = latLng2;
                if (parcel.readInt() != 0) {
                    cm = LatLng.CREATOR.cM(parcel);
                }
                this.setPosition(cm);
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                LatLng cm2 = latLng;
                if (parcel.readInt() != 0) {
                    cm2 = LatLng.CREATOR.cM(parcel);
                }
                this.setPositionWithRadius(cm2, parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final StreetViewPanoramaLocation streetViewPanoramaLocation = this.getStreetViewPanoramaLocation();
                parcel2.writeNoException();
                if (streetViewPanoramaLocation != null) {
                    parcel2.writeInt(1);
                    streetViewPanoramaLocation.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                this.setOnStreetViewPanoramaChangeListener(q$a.bh(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                this.setOnStreetViewPanoramaCameraChangeListener(p$a.bg(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                this.setOnStreetViewPanoramaClickListener(r$a.bi(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                final StreetViewPanoramaOrientation pointToOrientation = this.pointToOrientation(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                if (pointToOrientation != null) {
                    parcel2.writeInt(1);
                    pointToOrientation.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                StreetViewPanoramaOrientation ct;
                if (parcel.readInt() != 0) {
                    ct = StreetViewPanoramaOrientation.CREATOR.cT(parcel);
                }
                else {
                    ct = null;
                }
                final d orientationToPoint = this.orientationToPoint(ct);
                parcel2.writeNoException();
                IBinder binder2 = binder;
                if (orientationToPoint != null) {
                    binder2 = orientationToPoint.asBinder();
                }
                parcel2.writeStrongBinder(binder2);
                return true;
            }
        }
    }
}
