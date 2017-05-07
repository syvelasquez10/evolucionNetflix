// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface gz extends IInterface
{
    void a(final int p0, final hb p1) throws RemoteException;
    
    public abstract static class a extends Binder implements gz
    {
        public static gz N(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
            if (queryLocalInterface != null && queryLocalInterface instanceof gz) {
                return (gz)queryLocalInterface;
            }
            return new gz.a.a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
            switch (int1) {
                default: {
                    return super.onTransact(int1, parcel, parcel2, n);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    int1 = parcel.readInt();
                    hb an;
                    if (parcel.readInt() != 0) {
                        an = hb.CREATOR.an(parcel);
                    }
                    else {
                        an = null;
                    }
                    this.a(int1, an);
                    return true;
                }
            }
        }
        
        private static class a implements gz
        {
            private IBinder dU;
            
            a(final IBinder du) {
                this.dU = du;
            }
            
            @Override
            public void a(final int n, final hb hb) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.internal.IPlacesCallbacks");
                    obtain.writeInt(n);
                    if (hb != null) {
                        obtain.writeInt(1);
                        hb.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.dU.transact(1, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
            
            public IBinder asBinder() {
                return this.dU;
            }
        }
    }
}
