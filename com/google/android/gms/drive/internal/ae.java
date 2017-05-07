// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Binder;
import android.os.RemoteException;
import android.os.IInterface;

public interface ae extends IInterface
{
    void L(final boolean p0) throws RemoteException;
    
    public abstract static class a extends Binder implements ae
    {
        public static ae X(final IBinder binder) {
            if (binder == null) {
                return null;
            }
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
            if (queryLocalInterface != null && queryLocalInterface instanceof ae) {
                return (ae)queryLocalInterface;
            }
            return new ae.a.a(binder);
        }
        
        public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.google.android.gms.drive.internal.IEventReleaseCallback");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.google.android.gms.drive.internal.IEventReleaseCallback");
                    this.L(parcel.readInt() != 0);
                    return true;
                }
            }
        }
        
        private static class a implements ae
        {
            private IBinder lb;
            
            a(final IBinder lb) {
                this.lb = lb;
            }
            
            @Override
            public void L(final boolean b) throws RemoteException {
                int n = 1;
                final Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IEventReleaseCallback");
                    if (!b) {
                        n = 0;
                    }
                    obtain.writeInt(n);
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
