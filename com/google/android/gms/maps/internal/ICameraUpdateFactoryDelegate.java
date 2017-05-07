// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.CameraPosition;
import android.os.IInterface;

public interface ICameraUpdateFactoryDelegate extends IInterface
{
    d newCameraPosition(final CameraPosition p0) throws RemoteException;
    
    d newLatLng(final LatLng p0) throws RemoteException;
    
    d newLatLngBounds(final LatLngBounds p0, final int p1) throws RemoteException;
    
    d newLatLngBoundsWithSize(final LatLngBounds p0, final int p1, final int p2, final int p3) throws RemoteException;
    
    d newLatLngZoom(final LatLng p0, final float p1) throws RemoteException;
    
    d scrollBy(final float p0, final float p1) throws RemoteException;
    
    d zoomBy(final float p0) throws RemoteException;
    
    d zoomByWithFocus(final float p0, final int p1, final int p2) throws RemoteException;
    
    d zoomIn() throws RemoteException;
    
    d zoomOut() throws RemoteException;
    
    d zoomTo(final float p0) throws RemoteException;
    
    public abstract static class a extends Binder implements ICameraUpdateFactoryDelegate
    {
        public static ICameraUpdateFactoryDelegate aN(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof ICameraUpdateFactoryDelegate) {
                return (ICameraUpdateFactoryDelegate)queryLocalInterface;
            }
            return new ICameraUpdateFactoryDelegate.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
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
        
        private static class a implements ICameraUpdateFactoryDelegate
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public d newCameraPosition(final CameraPosition cameraPosition) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (cameraPosition != null) {
                        obtain.writeInt(1);
                        cameraPosition.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d newLatLng(final LatLng latLng) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d newLatLngBounds(final LatLngBounds latLngBounds, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    this.lb.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d newLatLngBoundsWithSize(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    this.lb.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d newLatLngZoom(final LatLng latLng, final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    obtain.writeFloat(n);
                    this.lb.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d scrollBy(final float n, final float n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(n);
                    obtain.writeFloat(n2);
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d zoomBy(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(n);
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d zoomByWithFocus(final float n, final int n2, final int n3) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(n);
                    obtain.writeInt(n2);
                    obtain.writeInt(n3);
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d zoomIn() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d zoomOut() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d zoomTo(final float n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
                    obtain.writeFloat(n);
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
