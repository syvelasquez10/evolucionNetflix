// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.IInterface;

public interface IStreetViewPanoramaDelegate extends IInterface
{
    void animateTo(final StreetViewPanoramaCamera p0, final long p1) throws RemoteException;
    
    void enablePanning(final boolean p0) throws RemoteException;
    
    void enableStreetNames(final boolean p0) throws RemoteException;
    
    void enableUserNavigation(final boolean p0) throws RemoteException;
    
    void enableZoom(final boolean p0) throws RemoteException;
    
    StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException;
    
    StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException;
    
    boolean isPanningGesturesEnabled() throws RemoteException;
    
    boolean isStreetNamesEnabled() throws RemoteException;
    
    boolean isUserNavigationEnabled() throws RemoteException;
    
    boolean isZoomGesturesEnabled() throws RemoteException;
    
    d orientationToPoint(final StreetViewPanoramaOrientation p0) throws RemoteException;
    
    StreetViewPanoramaOrientation pointToOrientation(final d p0) throws RemoteException;
    
    void setOnStreetViewPanoramaCameraChangeListener(final p p0) throws RemoteException;
    
    void setOnStreetViewPanoramaChangeListener(final q p0) throws RemoteException;
    
    void setOnStreetViewPanoramaClickListener(final r p0) throws RemoteException;
    
    void setPosition(final LatLng p0) throws RemoteException;
    
    void setPositionWithID(final String p0) throws RemoteException;
    
    void setPositionWithRadius(final LatLng p0, final int p1) throws RemoteException;
    
    public abstract static class a extends Binder implements IStreetViewPanoramaDelegate
    {
        public static IStreetViewPanoramaDelegate bl(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IStreetViewPanoramaDelegate) {
                return (IStreetViewPanoramaDelegate)queryLocalInterface;
            }
            return new IStreetViewPanoramaDelegate.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
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
                    this.setOnStreetViewPanoramaChangeListener(q.a.bh(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 16: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.setOnStreetViewPanoramaCameraChangeListener(p.a.bg(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 17: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.setOnStreetViewPanoramaClickListener(r.a.bi(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 18: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    final StreetViewPanoramaOrientation pointToOrientation = this.pointToOrientation(d.a.am(parcel.readStrongBinder()));
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
        
        private static class a implements IStreetViewPanoramaDelegate
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void animateTo(final StreetViewPanoramaCamera streetViewPanoramaCamera, final long n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (streetViewPanoramaCamera != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaCamera.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeLong(n);
                    this.lb.transact(9, obtain, obtain2, 0);
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
            public void enablePanning(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void enableStreetNames(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void enableUserNavigation(final boolean b) throws RemoteException {
                int n = 0;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (b) {
                        n = 1;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void enableZoom(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    StreetViewPanoramaCamera cq;
                    if (obtain2.readInt() != 0) {
                        cq = StreetViewPanoramaCamera.CREATOR.cQ(obtain2);
                    }
                    else {
                        cq = null;
                    }
                    return cq;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    StreetViewPanoramaLocation cs;
                    if (obtain2.readInt() != 0) {
                        cs = StreetViewPanoramaLocation.CREATOR.cS(obtain2);
                    }
                    else {
                        cs = null;
                    }
                    return cs;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isPanningGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(6, obtain, obtain2, 0);
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
            public boolean isStreetNamesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(8, obtain, obtain2, 0);
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
            public boolean isUserNavigationEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(7, obtain, obtain2, 0);
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
            public boolean isZoomGesturesEnabled() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    this.lb.transact(5, obtain, obtain2, 0);
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
            public d orientationToPoint(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (streetViewPanoramaOrientation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOrientation.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public StreetViewPanoramaOrientation pointToOrientation(final d d) throws RemoteException {
                final StreetViewPanoramaOrientation streetViewPanoramaOrientation = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    StreetViewPanoramaOrientation ct = streetViewPanoramaOrientation;
                    if (obtain2.readInt() != 0) {
                        ct = StreetViewPanoramaOrientation.CREATOR.cT(obtain2);
                    }
                    return ct;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnStreetViewPanoramaCameraChangeListener(final p p) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    IBinder binder;
                    if (p != null) {
                        binder = p.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnStreetViewPanoramaChangeListener(final q q) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    IBinder binder;
                    if (q != null) {
                        binder = q.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setOnStreetViewPanoramaClickListener(final r r) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    IBinder binder;
                    if (r != null) {
                        binder = r.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPosition(final LatLng latLng) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPositionWithID(final String s) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    obtain.writeString(s);
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void setPositionWithRadius(final LatLng latLng, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.lb.transact(13, obtain, obtain2, 0);
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
