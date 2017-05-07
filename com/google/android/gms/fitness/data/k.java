// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface k extends IInterface
{
    void onEvent(final DataPoint p0) throws RemoteException;
    
    public abstract static class a extends Binder implements k
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.fitness.data.IDataSourceListener");
        }
        
        public static k an(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener");
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
                    parcel2.writeString("com.google.android.gms.fitness.data.IDataSourceListener");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.fitness.data.IDataSourceListener");
                    DataPoint dataPoint;
                    if (parcel.readInt() != 0) {
                        dataPoint = (DataPoint)DataPoint.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        dataPoint = null;
                    }
                    this.onEvent(dataPoint);
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
            public void onEvent(final DataPoint dataPoint) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.data.IDataSourceListener");
                    if (dataPoint != null) {
                        obtain.writeInt(1);
                        dataPoint.writeToParcel(obtain, 0);
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
        }
    }
}
