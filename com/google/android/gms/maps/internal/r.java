// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import android.os.IInterface;

public interface r extends IInterface
{
    void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation p0) throws RemoteException;
    
    public abstract static class a extends Binder implements r
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
        }
        
        public static r bi(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof r) {
                return (r)queryLocalInterface;
            }
            return new r.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                    StreetViewPanoramaOrientation ct;
                    if (parcel.readInt() != 0) {
                        ct = StreetViewPanoramaOrientation.CREATOR.cT(parcel);
                    }
                    else {
                        ct = null;
                    }
                    this.onStreetViewPanoramaClick(ct);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements r
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
                    if (streetViewPanoramaOrientation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaOrientation.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(1, obtain, obtain2, 0);
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
