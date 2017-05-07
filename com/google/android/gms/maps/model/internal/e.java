// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface e extends IInterface
{
    boolean a(final e p0) throws RemoteException;
    
    void activate() throws RemoteException;
    
    String getName() throws RemoteException;
    
    String getShortName() throws RemoteException;
    
    int hashCodeRemote() throws RemoteException;
    
    public abstract static class a extends Binder implements e
    {
        public static e aF(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
            if (queryLocalInterface != null && queryLocalInterface instanceof e) {
                return (e)queryLocalInterface;
            }
            return new e.a.a(binder);
        }
        
        public boolean onTransact(int hashCodeRemote, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            switch (hashCodeRemote) {
                default: {
                    return super.onTransact(hashCodeRemote, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    final String name = this.getName();
                    parcel2.writeNoException();
                    parcel2.writeString(name);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    final String shortName = this.getShortName();
                    parcel2.writeNoException();
                    parcel2.writeString(shortName);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    this.activate();
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    final boolean a = this.a(aF(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a) {
                        hashCodeRemote = 1;
                    }
                    else {
                        hashCodeRemote = 0;
                    }
                    parcel2.writeInt(hashCodeRemote);
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    hashCodeRemote = this.hashCodeRemote();
                    parcel2.writeNoException();
                    parcel2.writeInt(hashCodeRemote);
                    return true;
                }
            }
        }
        
        private static class a implements e
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public boolean a(final e e) throws RemoteException {
                boolean b = false;
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    IBinder binder;
                    if (e != null) {
                        binder = e.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.kn.transact(4, obtain, obtain2, 0);
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
            public void activate() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    this.kn.transact(3, obtain, obtain2, 0);
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
            public String getName() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    this.kn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public String getShortName() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    this.kn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
                    this.kn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
