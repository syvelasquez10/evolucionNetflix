// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.Bundle;
import com.google.android.gms.dynamic.d;
import android.os.RemoteException;
import android.os.IInterface;

public interface IStreetViewPanoramaViewDelegate extends IInterface
{
    IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException;
    
    d getView() throws RemoteException;
    
    void onCreate(final Bundle p0) throws RemoteException;
    
    void onDestroy() throws RemoteException;
    
    void onLowMemory() throws RemoteException;
    
    void onPause() throws RemoteException;
    
    void onResume() throws RemoteException;
    
    void onSaveInstanceState(final Bundle p0) throws RemoteException;
    
    public abstract static class a extends Binder implements IStreetViewPanoramaViewDelegate
    {
        public static IStreetViewPanoramaViewDelegate bn(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IStreetViewPanoramaViewDelegate) {
                return (IStreetViewPanoramaViewDelegate)queryLocalInterface;
            }
            return new IStreetViewPanoramaViewDelegate.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final Bundle bundle = null;
            final Bundle bundle2 = null;
            final IBinder binder = null;
            final IBinder binder2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    final IStreetViewPanoramaDelegate streetViewPanorama = this.getStreetViewPanorama();
                    parcel2.writeNoException();
                    IBinder binder3 = binder2;
                    if (streetViewPanorama != null) {
                        binder3 = streetViewPanorama.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    Bundle bundle3 = bundle;
                    if (parcel.readInt() != 0) {
                        bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.onCreate(bundle3);
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.onResume();
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.onPause();
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.onDestroy();
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.onLowMemory();
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    Bundle bundle4 = bundle2;
                    if (parcel.readInt() != 0) {
                        bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                    }
                    this.onSaveInstanceState(bundle4);
                    parcel2.writeNoException();
                    if (bundle4 != null) {
                        parcel2.writeInt(1);
                        bundle4.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    final d view = this.getView();
                    parcel2.writeNoException();
                    IBinder binder4 = binder;
                    if (view != null) {
                        binder4 = view.asBinder();
                    }
                    parcel2.writeStrongBinder(binder4);
                    return true;
                }
            }
        }
        
        private static class a implements IStreetViewPanoramaViewDelegate
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return IStreetViewPanoramaDelegate.a.bl(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d getView() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
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
            public void onCreate(final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onDestroy() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.lb.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onLowMemory() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onPause() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.lb.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onResume() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void onSaveInstanceState(final Bundle bundle) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
