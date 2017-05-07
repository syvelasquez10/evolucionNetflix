// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.maps.model.VisibleRegion;
import android.os.RemoteException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import android.os.IInterface;

public interface IProjectionDelegate extends IInterface
{
    LatLng fromScreenLocation(final d p0) throws RemoteException;
    
    VisibleRegion getVisibleRegion() throws RemoteException;
    
    d toScreenLocation(final LatLng p0) throws RemoteException;
    
    public abstract static class a extends Binder implements IProjectionDelegate
    {
        public static IProjectionDelegate bj(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof IProjectionDelegate) {
                return (IProjectionDelegate)queryLocalInterface;
            }
            return new IProjectionDelegate.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final IBinder binder = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    final LatLng fromScreenLocation = this.fromScreenLocation(d.a.am(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (fromScreenLocation != null) {
                        parcel2.writeInt(1);
                        fromScreenLocation.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    LatLng cm;
                    if (parcel.readInt() != 0) {
                        cm = LatLng.CREATOR.cM(parcel);
                    }
                    else {
                        cm = null;
                    }
                    final d screenLocation = this.toScreenLocation(cm);
                    parcel2.writeNoException();
                    IBinder binder2 = binder;
                    if (screenLocation != null) {
                        binder2 = screenLocation.asBinder();
                    }
                    parcel2.writeStrongBinder(binder2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    final VisibleRegion visibleRegion = this.getVisibleRegion();
                    parcel2.writeNoException();
                    if (visibleRegion != null) {
                        parcel2.writeInt(1);
                        visibleRegion.writeToParcel(parcel2, 1);
                    }
                    else {
                        parcel2.writeInt(0);
                    }
                    return true;
                }
            }
        }
        
        private static class a implements IProjectionDelegate
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public LatLng fromScreenLocation(final d d) throws RemoteException {
                final LatLng latLng = null;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    LatLng cm = latLng;
                    if (obtain2.readInt() != 0) {
                        cm = LatLng.CREATOR.cM(obtain2);
                    }
                    return cm;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public VisibleRegion getVisibleRegion() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    VisibleRegion cw;
                    if (obtain2.readInt() != 0) {
                        cw = VisibleRegion.CREATOR.cW(obtain2);
                    }
                    else {
                        cw = null;
                    }
                    return cw;
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public d toScreenLocation(final LatLng latLng) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
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
