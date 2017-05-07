// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.IInterface;

public interface p extends IInterface
{
    void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera p0) throws RemoteException;
    
    public abstract static class a extends Binder implements p
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
        }
        
        public static p bg(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof p) {
                return (p)queryLocalInterface;
            }
            return new p.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    StreetViewPanoramaCamera cq;
                    if (parcel.readInt() != 0) {
                        cq = StreetViewPanoramaCamera.CREATOR.cQ(parcel);
                    }
                    else {
                        cq = null;
                    }
                    this.onStreetViewPanoramaCameraChange(cq);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements p
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera streetViewPanoramaCamera) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
                    if (streetViewPanoramaCamera != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaCamera.writeToParcel(obtain, 0);
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
