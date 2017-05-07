// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import android.os.IInterface;

public interface k extends IInterface
{
    void onDeviceFound(final BleDevice p0) throws RemoteException;
    
    void onScanStopped() throws RemoteException;
    
    public abstract static class a extends Binder implements k
    {
        public a() {
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
            return new k.a.a(binder);
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
        
        private static class a implements k
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void onDeviceFound(final BleDevice bleDevice) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.request.IBleScanCallback");
                    if (bleDevice != null) {
                        obtain.writeInt(1);
                        bleDevice.writeToParcel(obtain, 0);
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
            public void onScanStopped() throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.request.IBleScanCallback");
                    this.lb.transact(2, obtain, (Parcel)null, 1);
                }
                finally {
                    obtain.recycle();
                }
            }
        }
    }
}
