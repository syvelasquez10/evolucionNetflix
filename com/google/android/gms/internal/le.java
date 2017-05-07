// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;
import android.os.IInterface;

public interface le extends IInterface
{
    void a(final BleDevicesResult p0) throws RemoteException;
    
    public abstract static class a extends Binder implements le
    {
        public a() {
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
            return new le.a.a(binder);
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
        
        private static class a implements le
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final BleDevicesResult bleDevicesResult) throws RemoteException {
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
    }
}
