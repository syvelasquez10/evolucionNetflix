// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.d$a;
import com.google.android.gms.maps.model.VisibleRegion;
import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.dynamic.d;
import android.os.IBinder;

class IProjectionDelegate$a$a implements IProjectionDelegate
{
    private IBinder lb;
    
    IProjectionDelegate$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public LatLng fromScreenLocation(final d d) {
        final LatLng latLng = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            IBinder binder;
            if (d != null) {
                binder = d.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            LatLng cm = latLng;
            if (obtain2.readInt() != 0) {
                cm = LatLng.CREATOR.cM(obtain2);
            }
            return cm;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public VisibleRegion getVisibleRegion() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            this.lb.transact(3, obtain, obtain2, 0);
            obtain2.readException();
            VisibleRegion cw;
            if (obtain2.readInt() != 0) {
                cw = VisibleRegion.CREATOR.cW(obtain2);
            }
            else {
                cw = null;
            }
            return cw;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public d toScreenLocation(final LatLng latLng) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
            if (latLng != null) {
                obtain.writeInt(1);
                latLng.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return d$a.am(obtain2.readStrongBinder());
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
