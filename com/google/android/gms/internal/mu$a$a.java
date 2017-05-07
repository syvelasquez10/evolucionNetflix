// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.IBinder;

class mu$a$a implements mu
{
    private IBinder lb;
    
    mu$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void W(final DataHolder dataHolder) {
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
    public void X(final DataHolder dataHolder) {
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
    public void Y(final DataHolder dataHolder) {
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
