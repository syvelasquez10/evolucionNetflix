// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import android.os.IBinder;

class p$a$a implements p
{
    private IBinder lb;
    
    p$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
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
