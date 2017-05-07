// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface mu extends IInterface
{
    void W(final DataHolder p0) throws RemoteException;
    
    void X(final DataHolder p0) throws RemoteException;
    
    void Y(final DataHolder p0) throws RemoteException;
    
    public abstract static class a extends Binder implements mu
    {
        public static mu aM(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof mu) {
                return (mu)queryLocalInterface;
            }
            return new mu.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final DataHolder dataHolder = null;
            final DataHolder dataHolder2 = null;
            final DataHolder dataHolder3 = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    DataHolder z = dataHolder3;
                    if (parcel.readInt() != 0) {
                        z = DataHolder.CREATOR.z(parcel);
                    }
                    this.W(z);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    DataHolder z2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        z2 = DataHolder.CREATOR.z(parcel);
                    }
                    this.X(z2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    DataHolder z3 = dataHolder2;
                    if (parcel.readInt() != 0) {
                        z3 = DataHolder.CREATOR.z(parcel);
                    }
                    this.Y(z3);
                    return true;
                }
            }
        }
        
        private static class a implements mu
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void W(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void X(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void Y(final DataHolder dataHolder) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(3, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
