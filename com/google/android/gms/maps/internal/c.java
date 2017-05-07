// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.maps.model.internal.a;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface c extends IInterface
{
    IMapViewDelegate a(final d p0, final GoogleMapOptions p1) throws RemoteException;
    
    IStreetViewPanoramaViewDelegate a(final d p0, final StreetViewPanoramaOptions p1) throws RemoteException;
    
    void a(final d p0, final int p1) throws RemoteException;
    
    void g(final d p0) throws RemoteException;
    
    IMapFragmentDelegate h(final d p0) throws RemoteException;
    
    IStreetViewPanoramaFragmentDelegate i(final d p0) throws RemoteException;
    
    ICameraUpdateFactoryDelegate ix() throws RemoteException;
    
    com.google.android.gms.maps.model.internal.a iy() throws RemoteException;
    
    public abstract static class a extends Binder implements c
    {
        public static c ab(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            if (queryLocalInterface != null && queryLocalInterface instanceof c) {
                return (c)queryLocalInterface;
            }
            return new c.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final IBinder binder = null;
            final IBinder binder2 = null;
            final IBinder binder3 = null;
            final IBinder binder4 = null;
            final IBinder binder5 = null;
            final IBinder binder6 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.ICreator");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    this.g(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final IMapFragmentDelegate h = this.h(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder7 = binder6;
                    if (h != null) {
                        binder7 = h.asBinder();
                    }
                    parcel2.writeStrongBinder(binder7);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final d k = d.a.K(parcel.readStrongBinder());
                    GoogleMapOptions fromParcel;
                    if (parcel.readInt() != 0) {
                        fromParcel = GoogleMapOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel = null;
                    }
                    final IMapViewDelegate a = this.a(k, fromParcel);
                    parcel2.writeNoException();
                    IBinder binder8 = binder;
                    if (a != null) {
                        binder8 = a.asBinder();
                    }
                    parcel2.writeStrongBinder(binder8);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final ICameraUpdateFactoryDelegate ix = this.ix();
                    parcel2.writeNoException();
                    IBinder binder9 = binder2;
                    if (ix != null) {
                        binder9 = ix.asBinder();
                    }
                    parcel2.writeStrongBinder(binder9);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final a iy = this.iy();
                    parcel2.writeNoException();
                    IBinder binder10 = binder3;
                    if (iy != null) {
                        binder10 = iy.asBinder();
                    }
                    parcel2.writeStrongBinder(binder10);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    this.a(d.a.K(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final d i = d.a.K(parcel.readStrongBinder());
                    StreetViewPanoramaOptions fromParcel2;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = StreetViewPanoramaOptions.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel2 = null;
                    }
                    final IStreetViewPanoramaViewDelegate a2 = this.a(i, fromParcel2);
                    parcel2.writeNoException();
                    IBinder binder11 = binder4;
                    if (a2 != null) {
                        binder11 = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(binder11);
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.ICreator");
                    final IStreetViewPanoramaFragmentDelegate j = this.i(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder12 = binder5;
                    if (j != null) {
                        binder12 = j.asBinder();
                    }
                    parcel2.writeStrongBinder(binder12);
                    return true;
                }
            }
        }
        
        private static class a implements c
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public IMapViewDelegate a(final d d, final GoogleMapOptions googleMapOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (googleMapOptions != null) {
                        obtain.writeInt(1);
                        googleMapOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return IMapViewDelegate.a.ag(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IStreetViewPanoramaViewDelegate a(final d d, final StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    if (streetViewPanoramaOptions != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOptions.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.kn.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return IStreetViewPanoramaViewDelegate.a.az(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final d d, final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeInt(n);
                    this.kn.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void g(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IMapFragmentDelegate h(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return IMapFragmentDelegate.a.af(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public IStreetViewPanoramaFragmentDelegate i(final d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return IStreetViewPanoramaFragmentDelegate.a.ay(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public ICameraUpdateFactoryDelegate ix() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.kn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return ICameraUpdateFactoryDelegate.a.Z(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public com.google.android.gms.maps.model.internal.a iy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.ICreator");
                    this.kn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.maps.model.internal.a.a.aB(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
