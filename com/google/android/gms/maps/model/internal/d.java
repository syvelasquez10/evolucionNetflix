// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.Binder;
import android.os.IBinder;
import java.util.List;
import android.os.RemoteException;
import android.os.IInterface;

public interface d extends IInterface
{
    boolean b(final d p0) throws RemoteException;
    
    int getActiveLevelIndex() throws RemoteException;
    
    int getDefaultLevelIndex() throws RemoteException;
    
    List<IBinder> getLevels() throws RemoteException;
    
    int hashCodeRemote() throws RemoteException;
    
    boolean isUnderground() throws RemoteException;
    
    public abstract static class a extends Binder implements d
    {
        public static d bs(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof d) {
                return (d)queryLocalInterface;
            }
            return new d.a.a(binder);
        }
        
        public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final int n3 = 0;
            final int n4 = 0;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    n = this.getActiveLevelIndex();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    n = this.getDefaultLevelIndex();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    final List<IBinder> levels = this.getLevels();
                    parcel2.writeNoException();
                    parcel2.writeBinderList((List)levels);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    final boolean underground = this.isUnderground();
                    parcel2.writeNoException();
                    n = n4;
                    if (underground) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    final boolean b = this.b(bs(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    n = n3;
                    if (b) {
                        n = 1;
                    }
                    parcel2.writeInt(n);
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    n = this.hashCodeRemote();
                    parcel2.writeNoException();
                    parcel2.writeInt(n);
                    return true;
                }
            }
        }
        
        private static class a implements d
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public boolean b(final d d) throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
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
            public int getActiveLevelIndex() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int getDefaultLevelIndex() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public List<IBinder> getLevels() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.lb.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (List<IBinder>)obtain2.createBinderArrayList();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public int hashCodeRemote() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.lb.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public boolean isUnderground() throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
                    this.lb.transact(4, obtain, obtain2, 0);
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
        }
    }
}
