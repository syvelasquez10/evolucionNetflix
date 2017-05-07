// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import android.os.IInterface;

public interface q extends IInterface
{
    void onStreetViewPanoramaChange(final StreetViewPanoramaLocation p0) throws RemoteException;
    
    public abstract static class a extends Binder implements q
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
        }
        
        public static q bh(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
            if (queryLocalInterface != null && queryLocalInterface instanceof q) {
                return (q)queryLocalInterface;
            }
            return new q.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    StreetViewPanoramaLocation cs;
                    if (parcel.readInt() != 0) {
                        cs = StreetViewPanoramaLocation.CREATOR.cS(parcel);
                    }
                    else {
                        cs = null;
                    }
                    this.onStreetViewPanoramaChange(cs);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements q
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onStreetViewPanoramaChange(final StreetViewPanoramaLocation streetViewPanoramaLocation) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
                    if (streetViewPanoramaLocation != null) {
                        obtain.writeInt(1);
                        streetViewPanoramaLocation.writeToParcel(obtain, 0);
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
