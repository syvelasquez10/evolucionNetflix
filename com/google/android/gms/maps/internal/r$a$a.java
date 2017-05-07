// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import android.os.IBinder;

class r$a$a implements r
{
    private IBinder lb;
    
    r$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
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
