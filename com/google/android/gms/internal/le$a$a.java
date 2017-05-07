// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.fitness.result.BleDevicesResult;
import android.os.IBinder;

class le$a$a implements le
{
    private IBinder lb;
    
    le$a$a(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void a(final BleDevicesResult bleDevicesResult) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
            if (bleDevicesResult != null) {
                obtain.writeInt(1);
                bleDevicesResult.writeToParcel(obtain, 0);
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
    
    public IBinder asBinder() {
        return this.lb;
    }
}
