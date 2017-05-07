// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import android.os.IInterface;

public interface kh extends IInterface
{
    void M(final DataHolder p0) throws RemoteException;
    
    void a(final ki p0) throws RemoteException;
    
    void a(final kk p0) throws RemoteException;
    
    void b(final kk p0) throws RemoteException;
    
    public abstract static class a extends Binder implements kh
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.wearable.internal.IWearableListener");
        }
        
        public IBinder asBinder() {
            return (IBinder)this;
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            final ki ki = null;
            final kk kk = null;
            final kk kk2 = null;
            final DataHolder dataHolder = null;
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.wearable.internal.IWearableListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    DataHolder fromParcel = dataHolder;
                    if (parcel.readInt() != 0) {
                        fromParcel = DataHolder.CREATOR.createFromParcel(parcel);
                    }
                    this.M(fromParcel);
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    ki ki2 = ki;
                    if (parcel.readInt() != 0) {
                        ki2 = (ki)com.google.android.gms.internal.ki.CREATOR.createFromParcel(parcel);
                    }
                    this.a(ki2);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    kk kk3 = kk;
                    if (parcel.readInt() != 0) {
                        kk3 = (kk)com.google.android.gms.internal.kk.CREATOR.createFromParcel(parcel);
                    }
                    this.a(kk3);
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.google.android.gms.wearable.internal.IWearableListener");
                    kk kk4 = kk2;
                    if (parcel.readInt() != 0) {
                        kk4 = (kk)com.google.android.gms.internal.kk.CREATOR.createFromParcel(parcel);
                    }
                    this.b(kk4);
                    return true;
                }
            }
        }
    }
}
