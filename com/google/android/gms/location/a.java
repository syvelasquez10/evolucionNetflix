// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.location.Location;
import android.os.IInterface;

public interface a extends IInterface
{
    void onLocationChanged(final Location p0) throws RemoteException;
    
    public abstract static class a extends Binder implements a
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.location.ILocationListener");
        }
        
        public static a U(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof a) {
                return (a)queryLocalInterface;
            }
            return new a(binder);
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.location.ILocationListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.location.ILocationListener");
                    Location location;
                    if (parcel.readInt() != 0) {
                        location = (Location)Location.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        location = null;
                    }
                    this.onLocationChanged(location);
                    return true;
                }
            }
        }
        
        private static class a implements com.google.android.gms.location.a
        {
            private IBinder kn;
            
            a(final IBinder kn) {
                this.kn = kn;
            }
            
            public IBinder asBinder() {
                return this.kn;
            }
            
            @Override
            public void onLocationChanged(final Location location) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.ILocationListener");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
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
        }
    }
}
