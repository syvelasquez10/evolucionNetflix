// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.BleDevice;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class k$a extends Binder implements k
{
    public k$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.request.IBleScanCallback");
    }
    
    public static k ay(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof k) {
            return (k)queryLocalInterface;
        }
        return new k$a$a(binder);
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
                parcel2.writeString("com.google.android.gms.fitness.request.IBleScanCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.request.IBleScanCallback");
                BleDevice bleDevice;
                if (parcel.readInt() != 0) {
                    bleDevice = (BleDevice)BleDevice.CREATOR.createFromParcel(parcel);
                }
                else {
                    bleDevice = null;
                }
                this.onDeviceFound(bleDevice);
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.fitness.request.IBleScanCallback");
                this.onScanStopped();
                return true;
            }
        }
    }
}
