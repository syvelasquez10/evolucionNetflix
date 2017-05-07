// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface i extends IInterface
{
    void o(final Status p0) throws RemoteException;
    
    public abstract static class a extends Binder implements i
    {
        public static i ae(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IErrorCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof i) {
                return (i)queryLocalInterface;
            }
            return new i.a.a(binder);
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
                    parcel2.writeString("com.google.android.gms.drive.realtime.internal.IErrorCallback");
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.google.android.gms.drive.realtime.internal.IErrorCallback");
                    Status fromParcel;
                    if (parcel.readInt() != 0) {
                        fromParcel = Status.CREATOR.createFromParcel(parcel);
                    }
                    else {
                        fromParcel = null;
                    }
                    this.o(fromParcel);
                    parcel2.writeNoException();
                    return true;
                }
            }
        }
        
        private static class a implements i
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            public IBinder asBinder() {
                return this.lb;
            }
            
            @Override
            public void o(final Status status) throws RemoteException {
                final Parcel obtain = Parcel.obtain();
                final Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IErrorCallback");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    this.lb.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
