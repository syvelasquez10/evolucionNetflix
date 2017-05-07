// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;
import android.os.IInterface;

public interface kl extends IInterface
{
    void a(final DataReadResult p0) throws RemoteException;
    
    public abstract static class a extends Binder implements kl
    {
        public a() {
            this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.IDataReadCallback");
        }
        
        public static kl ap(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof kl) {
                return (kl)queryLocalInterface;
            }
            return new kl.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.fitness.internal.IDataReadCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
                    DataReadResult dataReadResult;
                    if (parcel.readInt() != 0) {
                        dataReadResult = (DataReadResult)DataReadResult.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        dataReadResult = null;
                    }
                    this.a(dataReadResult);
                    return true;
                }
            }
        }
        
        private static class a implements kl
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void a(final DataReadResult dataReadResult) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IDataReadCallback");
                    if (dataReadResult != null) {
                        obtain.writeInt(1);
                        dataReadResult.writeToParcel(obtain, 0);
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
            
            public IBinder asBinder() {
                return this.lb;
            }
        }
    }
}
