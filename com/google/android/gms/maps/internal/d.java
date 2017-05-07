// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import android.os.IInterface;

public interface d extends IInterface
{
    b f(final com.google.android.gms.maps.model.internal.d p0) throws RemoteException;
    
    b g(final com.google.android.gms.maps.model.internal.d p0) throws RemoteException;
    
    public abstract static class a extends Binder implements d
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
        }
        
        public static d S(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
            if (queryLocalInterface != null && queryLocalInterface instanceof d) {
                return (d)queryLocalInterface;
            }
            return new d.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final IBinder binder = null;
            final IBinder binder2 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    final b f = this.f(com.google.android.gms.maps.model.internal.d.a.am(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder3 = binder2;
                    if (f != null) {
                        binder3 = f.asBinder();
                    }
                    parcel2.writeStrongBinder(binder3);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    final b g = this.g(com.google.android.gms.maps.model.internal.d.a.am(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    IBinder binder4 = binder;
                    if (g != null) {
                        binder4 = g.asBinder();
                    }
                    parcel2.writeStrongBinder(binder4);
                    return true;
                }
            }
        }
        
        private static class a implements d
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
            
            @Override
            public b f(final com.google.android.gms.maps.model.internal.d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return b.a.E(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public b g(final com.google.android.gms.maps.model.internal.d d) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (d != null) {
                        binder = d.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.dU.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return b.a.E(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
