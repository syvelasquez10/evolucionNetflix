// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface IGamesSignInCallbacks extends IInterface
{
    void T(final DataHolder p0) throws RemoteException;
    
    void U(final DataHolder p0) throws RemoteException;
    
    void b(final int p0, final Intent p1) throws RemoteException;
    
    void dD(final int p0) throws RemoteException;
    
    void dE(final int p0) throws RemoteException;
    
    void g(final DataHolder p0) throws RemoteException;
    
    public abstract static class Stub extends Binder implements IGamesSignInCallbacks
    {
        public Stub() {
            this.attachInterface((IInterface)this, "com.google.android.gms.games.internal.IGamesSignInCallbacks");
        }
        
        public static IGamesSignInCallbacks aC(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof IGamesSignInCallbacks) {
                return (IGamesSignInCallbacks)queryLocalInterface;
            }
            return new Proxy(binder);
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            final DataHolder dataHolder = null;
            final DataHolder dataHolder2 = null;
            final DataHolder dataHolder3 = null;
            Intent intent = null;
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    return true;
                }
                case 5001: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    int1 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                    }
                    this.b(int1, intent);
                    parcel2.writeNoException();
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    DataHolder z = dataHolder;
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.T(z);
                    parcel2.writeNoException();
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    DataHolder z2 = dataHolder2;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.U(z2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    this.dD(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5005: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    DataHolder z3 = dataHolder3;
                    if (parcel.readInt() != 0) {
                        z3 = DataHolder.CREATOR.z(parcel);
                    }
                    this.g(z3);
                    parcel2.writeNoException();
                    return true;
                }
                case 5006: {
                    parcel.enforceInterface("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    this.dE(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class Proxy implements IGamesSignInCallbacks
        {
            private IBinder lb;
            
            Proxy(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void T(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void U(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5003, obtain, obtain2, 0);
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
            public void b(final int n, final Intent intent) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    obtain.writeInt(n);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void dD(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    obtain.writeInt(n);
                    this.lb.transact(5004, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void dE(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    obtain.writeInt(n);
                    this.lb.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void g(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesSignInCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(5005, obtain, obtain2, 0);
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
