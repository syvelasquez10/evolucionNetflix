// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import com.google.android.gms.common.data.DataHolder;
import android.os.RemoteException;
import android.os.IInterface;

public interface ic extends IInterface
{
    void S(final int p0) throws RemoteException;
    
    void a(final int p0, final DataHolder p1) throws RemoteException;
    
    void a(final DataHolder p0) throws RemoteException;
    
    void e(final int p0, final int p1) throws RemoteException;
    
    void fq() throws RemoteException;
    
    public abstract static class a extends Binder implements ic
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.appstate.internal.IAppStateCallbacks");
        }
        
        public static ic J(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof ic) {
                return (ic)queryLocalInterface;
            }
            return new ic.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            final DataHolder dataHolder = null;
            DataHolder z = null;
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    return true;
                }
                case 5001: {
                    parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    int1 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(int1, z);
                    parcel2.writeNoException();
                    return true;
                }
                case 5002: {
                    parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    DataHolder z2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.a(z2);
                    parcel2.writeNoException();
                    return true;
                }
                case 5003: {
                    parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    this.e(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 5004: {
                    parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    this.fq();
                    parcel2.writeNoException();
                    return true;
                }
                case 5005: {
                    parcel.enforceInterface("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    this.S(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements ic
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void S(final int n) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    obtain.writeInt(n);
                    this.lb.transact(5005, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void a(final int n, final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    obtain.writeInt(n);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
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
            public void a(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
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
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void e(final int n, final int n2) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    obtain.writeInt(n);
                    obtain.writeInt(n2);
                    this.lb.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public void fq() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateCallbacks");
                    this.lb.transact(5004, obtain, obtain2, 0);
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
