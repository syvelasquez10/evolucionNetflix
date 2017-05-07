// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.f;
import android.os.IInterface;

public interface d extends IInterface
{
    com.google.android.gms.dynamic.d f(final f p0) throws RemoteException;
    
    com.google.android.gms.dynamic.d g(final f p0) throws RemoteException;
    
    public abstract static class a extends Binder implements d
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IInfoWindowAdapter");
        }
        
        public static d aR(final IBinder binder) {
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
                    final com.google.android.gms.dynamic.d f = this.f(com.google.android.gms.maps.model.internal.f.a.bu(parcel.readStrongBinder()));
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
                    final com.google.android.gms.dynamic.d g = this.g(f.a.bu(parcel.readStrongBinder()));
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
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public com.google.android.gms.dynamic.d f(final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
            
            @Override
            public com.google.android.gms.dynamic.d g(final f f) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IInfoWindowAdapter");
                    IBinder binder;
                    if (f != null) {
                        binder = f.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return com.google.android.gms.dynamic.d.a.am(obtain2.readStrongBinder());
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
