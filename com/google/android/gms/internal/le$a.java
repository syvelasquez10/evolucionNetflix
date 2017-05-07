// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.BleDevicesResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class le$a extends Binder implements le
{
    public le$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }
    
    public static le ax(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof le) {
            return (le)queryLocalInterface;
        }
        return new le$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
                BleDevicesResult bleDevicesResult;
                if (parcel.readInt() != 0) {
                    bleDevicesResult = (BleDevicesResult)BleDevicesResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    bleDevicesResult = null;
                }
                this.a(bleDevicesResult);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
