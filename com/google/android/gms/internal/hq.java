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

public interface hq extends IInterface
{
    void K(final DataHolder p0) throws RemoteException;
    
    void L(final DataHolder p0) throws RemoteException;
    
    public abstract static class a extends Binder implements hq
    {
        public static hq Y(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof hq) {
                return (hq)queryLocalInterface;
            }
            return new hq.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final DataHolder dataHolder = null;
            final DataHolder dataHolder2 = null;
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
                    DataHolder fromParcel = dataHolder2;
                    if (parcel.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.K(fromParcel);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    DataHolder fromParcel2 = dataHolder;
                    if (parcel.readInt() != 0) {
                        fromParcel2 = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.L(fromParcel2);
                    return true;
                }
            }
        }
        
        private static class a implements hq
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            @Override
            public void K(final DataHolder dataHolder) throws RemoteException {
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
                    this.kn.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            @Override
            public void L(final DataHolder dataHolder) throws RemoteException {
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
                    this.kn.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
        }
    }
}
